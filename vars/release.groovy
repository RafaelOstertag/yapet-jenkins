/**
 * Build dist tarball.
 */

/**
  * call `gmake distcheck`. It expects the directory `obj-default`
  *  to exists and being configure
  */
def checkDist() {
    stage("Check Dist") {
	dir("obj-default") {
	    sh "gmake distcheck"
	}
    }
}

def makeDist() {
    stage("Make dist tarballs") {
	dir("obj-default") {
	    sh "gmake dist-gzip dist-bzip2 dist-xz"
	}
    }
}

def isReleaseBranch() {
    return BRANCH_NAME.startsWith("release/")
}

def getRelease() {
    return BRANCH_NAME.split("/")[1]
}

def readVersionFromConfigureAc() {
    configureAc = readFile 'configure.ac'
    for (line in configureAc.split("\n") ) {
	if (line.startsWith("AC_INIT(") ) {
	    version = line
		.split(",")[1]
		.replace("[","")
		.replace("]","")
		.trim()
	    return version
	}
    }

    error "Unable to extract version from configure.ac"
}

def releasePreflight() {
    stage("Release Preflight") {
	releaseVersion = getRelease()
	echo "Branch is version $releaseVersion"
	
	configureVersion = readVersionFromConfigureAc()
	echo "configure.ac is version $configureVersion"
	
	if (releaseVersion != configureVersion) {
	    error "Release version '$release' does not match the version '$configureVersion' in configure.ac"
	}
	echo "Release preflight ok"
    }
}

def roll() {
    if (!isReleaseBranch()) {
    	echo "Not on release branch. Skipping."
	return
    }
    
    echo "On release branch. Going to roll release."
    releasePreflight()
    checkDist()
    makeDist()
}
