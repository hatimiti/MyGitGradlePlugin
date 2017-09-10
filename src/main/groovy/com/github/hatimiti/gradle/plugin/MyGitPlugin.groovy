package com.github.hatimiti.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository

class MyGitPlugin implements Plugin<Project> {
  @Override
  void apply(Project project) {
    def extension = new MyGitPluginExtension()
    project.extensions.mygit = extension
    project.task('cloneGitRepo') doLast {
      def gitCloneClient = Git.cloneRepository()
        .setURI("${extension.repositoryURI}")
        .setDirectory(new File("./${extension.cloneDirName}/${extension.repositoryName}"))
      gitCloneClient.call()
    }
    project.task('cleanGitRepo', type: Delete) doFirst {
      delete "./${extension.cloneDirName}"
    }
  }
}
