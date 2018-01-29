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

def autoconf() {
    stage(makeStageName("autoconf")) {
	touch "README"
	touch "ChangeLog"
	touch "libyacurs/ChangeLog"
	// AUTOCONF_VERSION and AUTOMAKE_VERSION is used on OpenBSD.
	withEnv(["AUTOCONF_VERSION=2.69", "AUTOMAKE_VERSION=1.15"]) {
	    sh "autoreconf -I m4 -i"
	}
    }
}

/**
 * Build for a given operating system.
 *
 * @param system operating system. Refer to buildProfiles.groovy.
 */
def build(operatingSystem) {
    buildProfiles.profiles[operatingSystem].each { profileName, profile ->
	environmentVariables = profile.env
	objectDirectoryName = "obj-" + profileName
    
	stage(makeStageName("configure " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh "../configure " + profile.flags.join(" ")
		}
	    }
	}
	stage(makeStageName("docs " + profileName)) {
	    dir (objectDirectoryName + '/doc') {
		withEnv(environmentVariables) {
		    sh '$MAKE -f Makefile.doc'
		}
	    }
	}
    
	stage(makeStageName("build " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh '$MAKE all'
		}
	    }
	}

	stage(makeStageName("check " + profileName)) {
	    dir (objectDirectoryName) {
		withEnv(environmentVariables) {
		    sh '$MAKE check'
		}
	    }
	}
    }
}
