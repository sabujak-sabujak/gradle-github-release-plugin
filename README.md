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

## usage
```groovy
plugins {
    id 'io.github.sabujak-sabujak'
}

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
