def checkDist() {
    stage("Check Dist") {
	directory("obj-default") {
	    sh "gmake distcheck"
	}
    }
}

def makeDist() {
    stage("Make dist tarballs") {
	directory("obj-default") {
	    sh "gmake dist-gzip dist-bzip2 dist-xz"
	}
    }
}

def matchReleaseBranch() {
    return BRANCH_NAME =~ /release\/([0-9]+(?:\.[0-9])+)/
}

def isReleaseBranch() {
    if (matchReleaseBranch()) {
	return true
    } else {
	return false
    }
}

def getRelease() {
    releaseMatch = matchReleaseBranch()
    if (releaseMatch) {
	return releaseMatch.group(1)
    }

    return null
}

def readVersionFromConfigureAc() {
    configureAc = readFile 'configure.ac'
    match = configureAc =~ /AC_INIT\(\[[a-zA-Z]+\],\[([0-9]+(?:\.[0-9])+\],/
    if (match) {
	return match.group(1)
    }
    error "No version found in configure.ac"
}

def releasePreflight() {
    stage("Release Preflight") {
	release = getRelease()
	configureVersion = readVersionFromConfigureAc()

	if (release != configureVersion) {
	    error "Release version '$release' does not match the version '$configureVersion' in configure.ac"
	}
    }
}

def roll() {
    if (!isReleaseBranch()) {
	echo "Not on release branch. Skipping."
	return
    }

    releasePreflight()
    checkDist()
    makeDist()
}
