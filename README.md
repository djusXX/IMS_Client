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

