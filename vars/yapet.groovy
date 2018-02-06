// Functions used by YAPET's Jenkinsfile.

/**
 * Make a stage name by appending the node name. This allows recording
 * the status of the 'same' stage for different nodes.
 */
def makeStageName(name) {
    return name + " " + NODE_NAME
}

def checkout() {
    stage(makeStageName("checkout")) {
	checkout scm
    }
}

def makeChangeLog() {
    stage(makeStageName("ChangeLog")) {
	sh "git log --stat > ChangeLog"
    }
}

def makeLibyacursSubmoduleChangeLog() {
    stage(makeStageName("ChangeLog submodule")) {
	dir("libyacurs") {
	    sh "git log --stat > ChangeLog"
	}
    }
}

def autoconf() {
    stage(makeStageName("autoconf")) {
	touch "README"
	// AUTOCONF_VERSION and AUTOMAKE_VERSION is used on OpenBSD.
	withEnv(["AUTOCONF_VERSION=2.69", "AUTOMAKE_VERSION=1.15"]) {
	    sh "autoreconf -I m4 -i"
	}
    }
}

def runWithProfile(operatingSystem, body) {

    ansiColor('xterm') {
	buildProfiles.profiles[operatingSystem].each { profileName, profile ->
	    environmentVariables = profile.env
	    objectDirectoryName = "obj-" + profileName

	    config = [
		"environmentVariables": environmentVariables,
		"profileName": profileName,
		"profile": profile,
		"objectDirectoryName": objectDirectoryName
	    ]
	    body.resolveStrategy = Closure.DELEGATE_FIRST
	    body.delegate = config
	    body()
	}
    }
}

/**
 * Configure for a given operating system
 *
 * @param operatingSystem operating system. Refer to buildProfiles.groovy.
 */
def configure(operatingSystem) {
    runWithProfile(operatingSystem) {
	stage(makeStageName("clean " + profileName)) {
	    sh "rm -rf $objectDirectoryName"
	}
	    
	stage(makeStageName("configure " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh "../configure " + profile.flags.join(" ")
		}
	    }
	}
    }
}

/**
 * Build YAPET documentation. This step is required when building
 * yapet since it creates the man pages needed for complete build
 *
 * @param operatingSystem operating system. Refer to buildProfiles.groovy.
 */
def buildDoc(operatingSystem) {
    runWithProfile(operatingSystem) {
	stage(makeStageName("docs " + profileName)) {
	    dir (objectDirectoryName + '/doc') {
		withEnv(environmentVariables) {
		    sh '$MAKE -f Makefile.doc'
		}
	    }
	}
    }
}

/**
 * Build.
 *
 * @param operatingSystem operating system. Refer to buildProfiles.groovy.
 */
def build(operatingSystem) {
    runWithProfile(operatingSystem) {
    	stage(makeStageName("build " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh '$MAKE all'
		}
	    }
	}
    }
}

/**
 * Build.
 *
 * @param operatingSystem operating system. Refer to buildProfiles.groovy.
 */
def check(operatingSystem) {
    runWithProfile(operatingSystem) {
	stage(makeStageName("check " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh '$MAKE check'
		}
	    }
	}
    }
}

def notify(body) {
    try {
	body()
	currentBuild.result = 'SUCCESS'
    } catch (e) {
	    currentBuild.result = 'FAILURE'
	    throw e
	} finally {
		emailext body: '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:

Check console output at $BUILD_URL to view the results.''', recipientProviders: [[$class: 'DevelopersRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'rafi@guengel.ch'
	    }
}
