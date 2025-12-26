# Linux Directory Permissions Assignment (Q1, Q2, Q3)

---

## Q1. Private Department Directories

### Question
Create a folder by name **shared** below `/`. Inside this `/shared` folder create folders by name **sales** and **purchase**.

- User **ramesh** works in sales department.
- User **roshani** works in purchase department.
- The **sales** folder should be accessible only to **ramesh**.
- The **purchase** folder should be accessible only to **roshani**.
- No other department users should be able to access these folders.
- Both users should be able to **create, delete, and rename** files only in their own folders.

---

### Steps
1. Ensure required users exist.
2. Create the directory structure under root `/`.
3. Assign ownership to the correct user.
4. Apply permissions so only the owner can access.
5. Verify permissions and test access.

---

### Commands
```bash
# Check users
id ramesh
id roshani

# Create users if not present
sudo adduser ramesh
sudo adduser roshani

# Create directories
sudo mkdir /shared
sudo mkdir /shared/sales
sudo mkdir /shared/purchase

# Assign ownership
sudo chown ramesh:ramesh /shared/sales
sudo chown roshani:roshani /shared/purchase

# Assign permissions (private folders)
sudo chmod 700 /shared/sales
sudo chmod 700 /shared/purchase

# Verify
ls -ld /shared/sales /shared/purchase
```

---

### Explanation of Commands

Below is a **detailed explanation of every command and option used**, written in simple exam‑oriented language.

- `sudo`: Stands for *Super User DO*. It is used to execute commands with **administrator (root) privileges**. Directories under `/` and user/group management require root access, so `sudo` is mandatory.

- `adduser <username>`: Creates a new user account in the system. Only root can create users, hence it is always used with `sudo`.

- `id <username>`: Checks whether a user exists and displays their UID, GID, and group memberships. Useful for verification before assigning ownership.

- `mkdir <directory>`: Creates a directory. When creating directories under `/`, `sudo` is required because `/` is owned by root.

- `chown user:group <directory>`: Changes the **ownership** of a file or directory. The first name is the owner (user) and the second is the group. Ownership determines who controls permissions.

- `chmod <permissions> <directory>`: Changes permission bits of a directory. Numeric values like `700` or `770` precisely define read, write, and execute permissions for owner, group, and others.

- `ls -ld <directory>`: Lists directory permissions and ownership. The `-l` option shows long details and `-d` ensures directory info is shown, not its contents.


- `sudo adduser <user>`: Creates a new user (requires root privileges).
- `mkdir`: Creates directories.
- `chown user:group`: Changes ownership of a file/directory.
- `chmod 700`: Grants read, write, execute permissions to the owner only; blocks group and others.
- `ls -ld`: Displays directory permissions and ownership.

---

## Q2. Project Directory with Group Access

### Question
Create a folder by name `/project`.

- User **mohan** is the project leader (owner) and should have full permissions.
- Users **ramesh** and **roshani** are developers and also require full permissions.
- Other users should not be able to access this folder.

---

### Steps
1. Create a group for developers.
2. Add developers to the group.
3. Create `/project` directory.
4. Assign ownership to mohan and the group.
5. Set permissions to allow only owner and group.
6. Verify and test access.

---

### Commands
```bash
# Check/create users
id mohan
sudo adduser mohan

# Create group
sudo groupadd projectgrp

# Add developers to group
sudo usermod -aG projectgrp ramesh
sudo usermod -aG projectgrp roshani

# Create project directory
sudo mkdir /project

# Assign ownership
sudo chown mohan:projectgrp /project

# Assign permissions
sudo chmod 770 /project

# Verify
ls -ld /project
```

---

### Explanation of Commands

Below is a **detailed explanation of every command and option used**, written in simple exam‑oriented language.

- `sudo`: Stands for *Super User DO*. It is used to execute commands with **administrator (root) privileges**. Directories under `/` and user/group management require root access, so `sudo` is mandatory.

- `adduser <username>`: Creates a new user account in the system. Only root can create users, hence it is always used with `sudo`.

- `id <username>`: Checks whether a user exists and displays their UID, GID, and group memberships. Useful for verification before assigning ownership.

- `mkdir <directory>`: Creates a directory. When creating directories under `/`, `sudo` is required because `/` is owned by root.

- `chown user:group <directory>`: Changes the **ownership** of a file or directory. The first name is the owner (user) and the second is the group. Ownership determines who controls permissions.

- `chmod <permissions> <directory>`: Changes permission bits of a directory. Numeric values like `700` or `770` precisely define read, write, and execute permissions for owner, group, and others.

- `ls -ld <directory>`: Lists directory permissions and ownership. The `-l` option shows long details and `-d` ensures directory info is shown, not its contents.


- `groupadd`: Creates a new group.
- `usermod -aG`: Adds a user to a group without removing existing group memberships.
- `chmod 770`: Full access for owner and group; no access for others.

---

## Q3. Data and Backup Directories

### Question
Create a folder `/data`.

- User **anjali** is the owner.
- Users **mohan, ramesh, roshani** are data entry operators and need full access.
- Other users must not access the directory.

Create a directory `/data-bak` such that:
- Only **anjali** and **root** can copy files into it.
- No other user should be allowed.

---

### Steps
1. Create users and group for data operators.
2. Create `/data` and `/data-bak` directories.
3. Assign ownership and permissions for `/data`.
4. Restrict `/data-bak` to owner only.
5. Verify and test copying behavior.

---

### Commands
```bash
# Check/create users
id anjali
sudo adduser anjali

# Create group for data operators
sudo groupadd datagrp

# Add users to group
sudo usermod -aG datagrp mohan
sudo usermod -aG datagrp ramesh
sudo usermod -aG datagrp roshani

# Create directories
sudo mkdir /data
sudo mkdir /data-bak

# Assign ownership to /data
sudo chown anjali:datagrp /data

# Set permissions for /data
sudo chmod 770 /data

# Assign ownership to /data-bak
sudo chown anjali:anjali /data-bak

# Restrict permissions for /data-bak
sudo chmod 700 /data-bak

# Verify
ls -ld /data /data-bak
```

---

### Explanation of Commands

Below is a **detailed explanation of every command and option used**, written in simple exam‑oriented language.

- `sudo`: Stands for *Super User DO*. It is used to execute commands with **administrator (root) privileges**. Directories under `/` and user/group management require root access, so `sudo` is mandatory.

- `adduser <username>`: Creates a new user account in the system. Only root can create users, hence it is always used with `sudo`.

- `id <username>`: Checks whether a user exists and displays their UID, GID, and group memberships. Useful for verification before assigning ownership.

- `mkdir <directory>`: Creates a directory. When creating directories under `/`, `sudo` is required because `/` is owned by root.

- `chown user:group <directory>`: Changes the **ownership** of a file or directory. The first name is the owner (user) and the second is the group. Ownership determines who controls permissions.

- `chmod <permissions> <directory>`: Changes permission bits of a directory. Numeric values like `700` or `770` precisely define read, write, and execute permissions for owner, group, and others.

- `ls -ld <directory>`: Lists directory permissions and ownership. The `-l` option shows long details and `-d` ensures directory info is shown, not its contents.


- `chmod 770 /data`: Owner and group have full permissions; others are denied.
- `chmod 700 /data-bak`: Only the owner has full permissions; others are denied.
- Copying files into a directory requires **write and execute** permissions on that directory.
- Root user can always access any directory regardless of permissions.

---

## Final Notes (Exam Ready)
- Use `chown` to control ownership.
- Use `chmod` to control access.
- Use groups to share access among multiple users.
- Always verify using `ls -ld` and test using `su - <user>`.

---

**End of Assignment**

