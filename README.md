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
    - add subscribing and receiving presence status
    - add views for presence status
    - add navigation graphs instead of fragmentManager transactions


## APP Architecture:
    MVVM Clean Architecture

    SIP related classes/interfaces:
        - configuration helper??
        - registration service: provide and keeps registration of configured Account
        - calling service: handle in/out voice/video calls => implement self-managed ConnectionService
        - messagging service: send/receive messages [required: added Buddy OR active Call]
        - presence service??
























