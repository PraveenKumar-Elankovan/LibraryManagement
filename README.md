# LibraryManagement

User has the following properties

  o id (User's ID uniquely identifies a user )
  o firstName (alphabets)
  o middleName (alphabets but it is optional)
  o lastName (alphabets)
  o age (valid non zero positive number)
  o gender (M or F)
  o phone (10-digit positive number)
  o zip (optional field)

Book has the following properties

  o id
  o name
  o authors (can have multiple authors)
  o checkedOutBy (user that has this book checked out)

Use ArrayList of objects as your data store. (for ex: one arraylist for storing users and one arraylist for storing 
books)

Services:

  o createUser 
      Creates the user if he is not already available in the data store.
  o getAllUsers
      Gives the list of all users registered in the library
  o updateUser
      Update an existing user
  o getAllBooks
      Gives list of all books in the library
  o findBookByName
      Given the name of the book, searches for any book that contains the given name
  o addBook
      Adds a new book to the inventory
  o checkOutBook
    Takes the user id and book id as inputs
    A book that is already checked out, cannot be checked out again (send proper error codes back) 
