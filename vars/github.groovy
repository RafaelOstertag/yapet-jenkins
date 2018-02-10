def isPullRequest() {
    return BRANCH_NAME.startsWith("PR-");
}

def getCommit() {
    return sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
}

def getRepositoryName() {
    repoComponents = sh(returnStdout: true, script: 'git config --get remote.origin.url')
	.trim()
	.split("/")
    gitRepoWithExtension = repoComponents[repoComponents.size() - 1]
    return gitRepoWithExtension.split("\\.")[0]
}
    

def notifyPending(message) {
    notify(message, 'PENDING')
}

def notifySuccess(message) {
    notify(message,'SUCCESS')
}

def notifyFailure(message) {
    notify(message,'FAILURE')
}

def notifyError(message) {
    notify(message,'ERROR')
}

def notify(message, status) {
    if (isPullRequest()) {
	gitCommit = getCommit()
	repoName = getRepositoryName()
	echo "Notify github $repoName for commit $gitCommit on $BRANCH_NAME"
	githubNotify account: 'RafaelOstertag', context: "jenkins/" + NODE_NAME, credentialsId: '4547e0fb-4542-457a-b52e-f579dbc08ea6', description: message, repo: repoName, sha: gitCommit, status: status
    } else {
	echo "Not notifiying Github, not a pull request"
    }
}


