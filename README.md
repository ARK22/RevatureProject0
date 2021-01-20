# RevatureProject0

## Console Menus
For convenience I've divided how my application works into the following categories.

This lists the menus in tha `app.java` file and what their role is
Menus| Description
------------ | ------------
frontPage()|The initial menu for the application where users can select to log in, register, or close the program. 
firstScreen()|Main menu for non-administrator users when being logged into their system.
superScreen()|Menu for Administrators when logged in that can allow for all required operations to be performed
accountScreen(Account selected_account)|Menu for performing operations on a User's account after being selected by the administrator
userScreen(User selectedUser)|Menu displaying a selected user and options on what to do to that user.
	
  This lists the forms and their roles in conjunction with the menus.
Forms| Description
------------ | ------------
handleLogin()|Acts as a form for taking in User's credentials before calling the login service.
register()|Acts as a form for taking in User's credentials and enforcing account creation policies, before calling the register service.
handleNewAccount()|Acts as a form for the creation of a new account.


## Services
Method | Description| Entity
------------ | ------------|------------
withdrawAction(int user_id, int account_id)| Calls 'updateAccountAmount' passing new value so long as amount withdrawn does not make the account go below zero.|Account
depositAction(int user_id, int account_id)|Calls 'updateAccountAmount' passing new value for balance.|Account
deleteAccountById(int account_id)|Calls the delete_account operation so long as the account has zero as its balance|Account
getAccountById(int account_id, int user_id)|Calls a prepared statement to retrieve account from DB based on ID|Account
appendAccount(String name,int user_id,int amount)|Adds account to a table with a Callablestatment|Account
getAccountTable(int user_id)|Returns a list containing all values in the account table that share a specific owner_id|Account
printAllAccountsForUser(List<Account> table)|Prints all accounts ordered by their account id.|Account
registerUsers(String username, String Password)|Adds new user to table calling user DAO|User
login(String username, String password)|Checks to see if the user's password matches the password on record and returns a User object if true.|User
getAllUsers()|FOR ADMIN: grabs a list of all users in the User table.|User
printAllUsers(List<User> table)|FOR ADMIN: prints a list of all users in the User table|User
getUserByName(String username)|Returns a User object if the user is found to be in the database by name, otherwise returns null|User
getUserById(int userId)|Returns a User object if the user is found to be in the database by id, otherwise returns null|User
updateUserById(User updatedUser)|Calls updatUserStatus DAO passing the newly altered User Object|User
deleteUser(User user)|Returns true if the user was found and deleted from the table, otherwise returns false.|User

## DAO
Method | Description|CRUD Operation
------------ | ------------|------------
createAccount(String givenName, int userId, int balance)|Adds newly created Account object to database with a prepared statement passing the new values provided by the Account object.|CREATE
updateAccountAmount(Account account)|Passes all values in a Account object to the database using the update_account_amount prepared call and replaces the values with the ones provided|UPDATE
getAccountById(int acctId,int user_id)|Returns Account object after running Prepared statement if Account is found,otherwise returns null.|READ
deleteAccount(int acctId)|Deletes account from database|DELETE
getAllAccountsById(int usrId)|Returns List object of type Account containing all Accounts in the database that match the provided 'owner_id'|READ
Register(User newUser)|Adds newly created user object to database with a prepared statement passing the new values provided by the User object.|CREATE
getUserById(int Id)|Returns User object after running Prepared statement if User is found,otherwise returns null.|READ
getUserByName(String name)|Returns User object after running Prepared statement if User is found,otherwise returns null.|READ	
getAllUsers()|Returns List object of type User containing all users in the database|READ
updateUserStatus(User user)|Passes all values in a user object to the database using the update_user_info prepared call and replaces the values with the ones provided|UPDATE
deleteUser(int id)|Deletes User from database|DELETE
