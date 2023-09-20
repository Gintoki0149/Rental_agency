# Rental_agency
**Keywords:** Android Studio, Java, Firebase, Realtime-Database<br>
## Table Structure (NoSQL)
- root table
  - user_list
    - firebase_generated  unique identifier for each user
      - email
      - first name
      - last name
      - password
      - phone
      - usernname(unique)
   - properties
     - firebase_generated unique identifier for each user
       - property name
         - Address
         - Availability
         - Bedrooms
         - City
         - Floor
         - Annual Rent Hike(%)
         - Owner Username
         - Pincode
         - Rent
         - State
         - Year of Construction
    - rent_requests
      - from
        - requesting_user_username
           - addressed_user_username
              - requeseted_property_name
                 - request_msg
                 - request_status
      - to
        - addressed_user_username
          - requesting_user_username
             - requested_property_name
               - request_msg
               - request_status
## Overview
- This is an android application made using Android Studio for renting properties and putting up properties for rent
- The application code has been written in Java
- Users can register by clicking on the sign up button
- Once created, user data is stored in the user_list table and an entry is reflected in the Firebase Authentication
- The landing page after logging in has options for:
  - Browsing through available properties for rent(properties not owned by the current user and have available status in the 'Availability' column)<br>
    On clicking a property card you can see all the details and the option to rent property becomes available
  - Going through the rent requests current user has sent for renting a property
  - Going through rent requests received for owned properties
  - Adding a new property
## Some Key Features
- What if many users try to rent the same property at the same time?<br>
  - A rent request will be created for each user and the owner can see the requests. Now the owner can select a user.
- But what if the user selects more than one tenant?<br>
  - This is not possible since as soon as the owner approves the rent request for a property, all requests for the same property are removed and the status of the request in the requesting users is shown as rejected. The selected user's request status shows accepted.
## Layout Description
- Login Page: It consists of a constraint layout with two buttons. One for login and one for signup.<br>
The login button takes the user to an activity where the user is asked to enter the email and password. On entering valid details the user the Landing Page Activity is opened.<br>
The signup button opens an activity which asks the user to fill out a user registration form. On clicking the submit button the details are verified to be valid and the user is created as described above.
- Landing Page: It is made of a constraint layout. A list view is nested which contains all the options available to a logged in user. <br>
                A list layout was used since there were a small and fixed number of options. Each option in the list view has an onClickListener which starts another activity using intents.
- Available Properties: The Activity has a recycler view. Each element is a summary of the available property in a cardView. Each card has an onClickListener which starts another activity and passes all the information of the clicked property to the new activity.
- Rent request pages: The layout again comprises of a recycler view with each element being a card view. Each card also has two buttons for accepting or rejecting the request in case of an owner and a button for removing the request in the case of a requesting user. These buttons call functions to accept the rent request(in which case all other requests for the same property are rejected), reject the request(in which case the status becomes rejected for the requesting user) or remove the request(in which case the status becomes as removed when the owner views the requests) respectively.
  
 
