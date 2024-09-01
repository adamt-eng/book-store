# Book Store Management System (Phase 1)

## Project Overview

The Book Store Management System is designed to facilitate online book shopping, catering to two types of users: **Admin** and **Reader**.

### User Roles

- **Admin**: Responsible for managing the book inventory.
- **Reader**: End users who can browse, search for, and purchase books.

### User Details

- **General User Information**:
  - Username
  - Email
  - Password (hashed for security)
  - All user information is encrypted using AES-256 encryption, ensuring that sensitive data is well protected.

- **Reader-Specific Information**:
  - Phone number
  - Address
  - Payment method

### Book Details

Each book in the system is characterized by the following attributes:

- Name
- Author
- Price
- Available stock quantity
- Category

## System Functionalities

### Admin Capabilities

The Admin has the following functionalities:

- **Add Books**: Insert new books into the inventory.
- **Edit Books**: Update details of existing books.
- **Delete Books**: Remove books from the inventory.
- **View All Books**: Display all books, including those out of stock.
- **Search Books**: Find books based on various criteria.

### Reader Capabilities

The Reader can perform the following actions:

- **Register Account**: Create a new user account.
- **Edit Profile**: Update personal information.
- **View Available Books**: Display a list of books currently in stock.
- **Search Books**: Find books by different attributes.
- **Order Books**: Purchase books, which automatically reduces the stock quantity. 
  - Order information is also encrypted using AES-256 encryption.

## Configuration

- The application stores its data in text files located in the `resources` directory, which includes `admins.txt`, `books.txt`, `orders.txt`, and `readers.txt`.
- All stored information is encrypted using AES-256 encryption, and passwords are securely hashed before storage.

## Phase 1 Details

Phase 1 focuses on developing the core functionalities of the Book Store Management System as a console-based application.

## Transition to Phase 2

After completing Phase 1, the project transitioned to Phase 2, where a graphical user interface (GUI) was developed. The codebase for the GUI can be found in the [book-store-gui](https://github.com/adamt-eng/book-store-gui) repository. The commits in the **book-store-gui** repository begin from a point where most of the core functionality was already established in this Phase 1 repository.

## Documentation

For comprehensive documentation of the final project, please refer to the README in the [book-store-gui](https://github.com/adamt-eng/book-store-gui) repository.
