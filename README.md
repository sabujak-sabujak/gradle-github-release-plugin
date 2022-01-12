```

                     _ _                  _ _   _           _      
                    | | |                (_) | | |         | |     
  __ _ _ __ __ _  __| | | ___ ______ __ _ _| |_| |__  _   _| |__   
 / _` | '__/ _` |/ _` | |/ _ \______/ _` | | __| '_ \| | | | '_ \  
| (_| | | | (_| | (_| | |  __/     | (_| | | |_| | | | |_| | |_) | 
 \__, |_|  \__,_|\__,_|_|\___|      \__, |_|\__|_| |_|\__,_|_.__/  
  __/ |                              __/ |                         
 |___/                              |___/                          
          _                                  _             _       
         | |                                | |           (_)      
 _ __ ___| | ___  __ _ ___  ___ ______ _ __ | |_   _  __ _ _ _ __  
| '__/ _ \ |/ _ \/ _` / __|/ _ \______| '_ \| | | | |/ _` | | '_ \ 
| | |  __/ |  __/ (_| \__ \  __/      | |_) | | |_| | (_| | | | | |
|_|  \___|_|\___|\__,_|___/\___|      | .__/|_|\__,_|\__, |_|_| |_|
                                      | |             __/ |        
                                      |_|            |___/         

```                                                                                                                                                           

[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/io/github/sabujak-sabujak/io.github.sabujak-sabujak.gradle.plugin/maven-metadata.xml.svg?colorB=007ec6&label=gradle)](https://plugins.gradle.org/plugin//io.github.sabujak-sabujak)

## usage
<details open><summary>Kotlin</summary>
  
```kotlin
Using the plugins DSL:
plugins {
  id("io.github.sabujak-sabujak") version "x.x.x"
}

buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("io.github.sabujak-sabujak:gradle-github-release-plugin:x.x.x")
  }
}

apply(plugin = "io.github.sabujak-sabujak")
```
 
</details>

<details><summary>Groovy</summary>
  
```groovy
Using the plugins DSL:
plugins {
  id "io.github.sabujak-sabujak" version "x.x.x"
}

Using legacy plugin application:
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "io.github.sabujak-sabujak:gradle-github-release-plugin:x.x.x"
  }
}

apply plugin: "io.github.sabujak-sabujak"
```
 </details>

### Use Plugin

```groovy
githubRelease {
    token = "your token"
    owner = "owner"
    repo = "repo nam"
    tagName = "x.x.x"
    targetCommitish = "branch name"
    name = "Release name"
    body = "Release body"
    prerelease = false
    discussionCategoryName = ""
    generateReleaseNotes = false
}
```


