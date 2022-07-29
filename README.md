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


    :ALL
        - simplify data models 


## NOTES:

    Features per view/Fragment in app:
== LoginFragment (UserViewModel)
- get user(name, passwd) and server name
- log in user
- add user to db
- get user (previously) saved in db


== BuddyListFragment (List of Conversations w/ Buddies)
- get list of user buddies saved in db
- for each buddy on the list: 
    - get buddyData (uri, name)
    - observe presence state


== NewBuddyFragment
- get buddy name and sipUri
- add buddy to user's buddyList
- save Buddy in DB   
        

== SingleChatFragment (Conversation w/Buddy)
- get messages from/to buddy saved in DB 
- send message to buddy
- call to Buddy
- listen to MainActivity on incoming messages from that Buddy


== (Pre)CallFragment (in/out); [Called by MainActivity, react properly on each button listed below]
- answer/decline/hangup incoming call
- cancel outgoing call
- switch camera, mute or end active call
- getCallMediaUseCase
- save ended(not active) call to DB


== SettingsFragment (User data, presence status)
- set user presence status
- change user displayName



=== Main Activity
- observe on incoming messages
- observe on incoming calls
- manage loading/navigate fragments || add navigationGraph

















