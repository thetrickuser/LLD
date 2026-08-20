# System Requirement: Multi-Tenant Library Management System

Design and implement an in-memory Low-Level Design (LLD) for a Multi-Tenant Library Management System. The system must support isolated library instances (tenants) while allowing platform-level administrative controls.

## Key Requirements

### Multi-Tenancy & Tenant Setup

- Tenant Onboarding: Register tenants (e.g., City Central Library, University Library).
- Tenant Isolation: Users, books, and transactions must belong strictly to their assigned tenant. A user registered in Tenant A cannot borrow books from Tenant B using Tenant A's account.
- Custom Configuration: Each tenant can set its own policies:
- Maximum books a member can borrow at a time.
- Borrowing duration (in days).
- Overdue fine per day.

### Inventory & User Management

- Books: Add, update, and search books by Title, Author, Genre, or ISBN.
- Book Copies: Manage multiple copies (book items) for a single book title, each with a unique barcode/identifier and status (AVAILABLE, BORROWED, RESERVED, LOST).
- Users: Support roles: MEMBER and LIBRARIAN within each tenant. 

### Core Operations

- Borrow Book: Check out an available copy to a member if they haven't reached their active limit.
- Return Book: Mark the copy available and calculate overdue fines based on the tenant's fine policy.
- Reserve Book: Allow members to reserve a copy if all copies of a book title are currently borrowed.

### Expectations & Guidelines

- Clean Code: Adhere to SOLID principles, clean object-oriented design, and design patterns (e.g., Factory, Strategy, Singleton, or Repository).
- Concurrency Handling: Ensure thread safety when multiple users attempt to borrow or reserve the same book copy simultaneously.
- In-Memory Storage: Do not use external databases. Implement in-memory data structures (e.g., ConcurrentHashMap) to manage state.

### Extensibility 

- The fine calculation engine and search filters should be easy to extend.

### Sample Inputs & Expected Flow

  1. Register Tenant:
      - TenantID: T1, Name: "Central Library", MaxBooks: 2, LoanDays: 14, FinePerDay: $1
      - TenantID: T2, Name: "Campus Library", MaxBooks: 5, LoanDays: 7, FinePerDay: $2

  2. Add Books (Tenant T1):
      - Add Book: ISBN "1234", Title "Clean Code", Author "Robert Martin"
      - Add Item Copy: Barcode "BC-101" to ISBN "1234"

  3. Borrow & Fine Flow:
      - User U1 (Tenant T1) borrows Copy "BC-101" on Day 1.
      - User U1 attempts to return Copy "BC-101" on Day 20.
      - Result: Overdue by 2 days (14-day limit). Fine generated = $2.
        
### Extension Challenges (Optional)

- Cross-Tenant Super Admin: Implement a global search feature for a platform admin to view aggregate book counts across all tenants without violating data privacy.
- Tiered Fine Strategy: Implement a dynamic fine strategy (e.g., $1/day for the first week overdue, $3/day thereafter).