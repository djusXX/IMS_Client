# IMS_MOBILE_CLIENT 

## App main features:
    - logging to server
    - incoming VOICE calls
    - outgoing VOICE calls
    - sending messages
    - receiving messages
    - incoming VIDEO calls
    - outgoing VIDEO calls
    - setting presence status
    - subscribing buddy's presence status
    - keep logged current user
    - logging current user with pin

## TODO:
    - update sipservice to be a background service
    - add subscribing and receiving presence status
    - add views for presence status
    - update view on subscription state change
    - add keep logged logic
    - add keep logged user interface 
    - create swiped tabs for messages and calls history using ViewPager2
    - add navigation graphs instead of fragmentManager transactions
    - use buildSrc dependencies in build.gradle files
    - add paging library for loading messages/buddies
    

## APP Architecture:
    Inverted dependencies as in https://developer.android.com/images/training/dependency-injection/5-graph-dynamic-modules.png


        MODULE_1    MODULE_2    MODULE_3    MODULE_4    MODULE_5
           |           |           |           |           |
           |           \_________  |  _________/           |
           \____________________  \|/  ___________________/
                                 \ | /
                                   |
                                   V
                                  APP 

    APP (ApplicationComponent)                                  :imsmobileclient


    FEATURE_MODULE_1                [Domain|Data|UI]            :login
    - module login 
    
    FEATURE_MODULE_2                [Domain|Data|UI]            :calls
    - module calls
        - sub-module incoming audio/video
        - sub-module outgoing audio/video

    FEATURE_MODULE_3                [Domain|Data|UI]            :conversations
    - module conversations/messages
    
    FEATURE_MODULE_4 (CORE)         [Domain]                    :sipservice
    - module sipUtils
        - module pjsua (sipservice)

    FEATURE_MODULE_5 (REPO)         [Data]                      :repository
    - module repository






## TMP_TODO:
    - add (logged) user table to DB
    - add dependecy injections for DB & Repository in :data
    - add appExecutors
    - add workerManager and di for it to provide sipservice in background 















=============================================================================================================

classpath 'com.android.tools.build:gradle:7.1.2'
classpath 'com.github.dcendents:android-maven-gradle-plugin:1.4.1'
classpath 'androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1'
        


implementation 'com.google.android.material:material:1.5.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
implementation 'androidx.constraintlayout:constraintlayout-compose:1.0.0'
implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
implementation 'androidx.appcompat:appcompat:1.4.1'
implementation 'androidx.recyclerview:recyclerview:1.2.1'
implementation 'androidx.annotation:annotation:1.3.0'
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1'
testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.1.3'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
implementation 'androidx.room:room-runtime:2.4.2'
annotationProcessor 'androidx.room:room-compiler:2.4.2'

<!-- implementation "androidx.navigation:navigation-fragment:2.4.1"
implementation "androidx.navigation:navigation-ui:2.4.1"
implementation "androidx.navigation:navigation-dynamic-features-fragment:2.4.1"
androidTestImplementation "androidx.navigation:navigation-testing:2.4.1"
implementation "androidx.navigation:navigation-compose:2.4.1" -->












