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


## APP Architecture:
    MVVM Clean Architecture

    SIP related classes/interfaces:
        - configuration helper??
        - registration service: provide and keeps registration of configured Account
        - calling service: handle in/out voice/video calls => implement self-managed ConnectionService
                           This ConnectionService should be implemented in pjsua2IMS project (OR maybe in app-ui???)
        - messagging service: send/receive messages [required: added Buddy OR active Call]
        - presence service??


## TODO:

    :app-ui
        - add UI/views for presence status
        - add navigation graphs instead of fragmentManager transactions
        ???

    :domain
        - update usecases to handle managing active calls, incoming messages etc.

    :pjsua2IMS
        - add implementations of serviceManagers
        ???

    :presentation
        - implement viewModels



























