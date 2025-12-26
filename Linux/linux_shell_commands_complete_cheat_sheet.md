# 🐧 Linux Shell Commands – Complete Detailed Cheat Sheet

This cheat sheet is **LAB EXAM + VIVA + REVISION READY**.  
It covers **basic commands, file handling, permissions, users, groups, process, system info, networking, and package management**, with **clear explanations below every command**.

---

## 1️⃣ BASIC NAVIGATION COMMANDS

> These commands help you **move around the Linux filesystem**. Almost every lab exam starts with these.

### Important Concepts
- Linux follows a **hierarchical directory structure** starting from `/` (root).
- Every user has a **home directory** under `/home/username`.
- Commands operate relative to the **current working directory** unless an absolute path is given.



### `pwd`
```bash
pwd
```
**Description:** Displays the *present working directory*. It tells you exactly where you are in the filesystem.

---

### `ls`
```bash
ls
ls -l
ls -a
ls -ld directory
```
**Description:** Lists files and directories.
- `-l` → long listing (permissions, owner, size)
- `-a` → shows hidden files
- `-d` → shows directory info, not contents

---

### `cd`
```bash
cd directory
cd ..
cd ~
cd /
```
**Description:** Changes the current directory.
- `..` → parent directory
- `~` → home directory
- `/` → root directory

---

## 2️⃣ FILE & DIRECTORY OPERATIONS

### `touch`
```bash
touch file.txt
```
**Description:** Creates an empty file or updates timestamp if file exists.

---

### `mkdir`
```bash
mkdir dir
mkdir -p dir1/dir2
```
**Description:** Creates directories.
- `-p` creates parent directories if they do not exist.

---

### `rmdir`
```bash
rmdir emptydir
```
**Description:** Deletes **empty directories only**.

---

### `rm`
```bash
rm file.txt
rm -r directory
rm -rf directory
```
**Description:** Deletes files and directories.
- `-r` recursive delete
- `-f` force delete (no prompt)

⚠️ **Dangerous command – use carefully**

---

### `cp`
```bash
cp file1 file2
cp file /path
cp -r dir1 dir2
```
**Description:** Copies files or directories.
- `-r` required for directories

---

### `mv`
```bash
mv old.txt new.txt
mv file /path
```
**Description:** Moves or renames files/directories.

---

## 3️⃣ FILE VIEWING COMMANDS

### `cat`
```bash
cat file.txt
```
**Description:** Displays entire file content.

---

### `less / more`
```bash
less file.txt
more file.txt
```
**Description:** Displays file content page by page.

---

### `head / tail`
```bash
head file.txt
tail file.txt
tail -n 20 file.txt
```
**Description:** Shows first or last lines of a file.

---

## 4️⃣ FILE PERMISSIONS & OWNERSHIP (VERY IMPORTANT)

> Linux security is based on **permissions and ownership**. This is one of the **most important exam topics**.

### Permission Model
Each file/directory has:
1. **Owner (user)**
2. **Group**
3. **Others**

Each category can have:
- **r (read)**
- **w (write)**
- **x (execute)**

### Permission Meaning for Directories
| Permission | Meaning |
|-----------|---------|
| r | list files inside directory |
| w | create/delete/rename files |
| x | enter directory |

> To copy a file into a directory, user needs **write + execute** permission.



### `chmod`
```bash
chmod 700 file
chmod 770 dir
chmod +x script.sh
```
**Description:** Changes permissions.
- 7 → rwx
- 6 → rw-
- 5 → r-x

`700` → owner only  
`770` → owner + group

---

### `chown`
```bash
sudo chown user file
sudo chown user:group dir
```
**Description:** Changes ownership of file or directory.

---

### `ls -l`
```bash
ls -l file
```
**Description:** Shows permissions, owner, group, size.

---

## 5️⃣ USER & GROUP MANAGEMENT

> User and group management commands require **root privileges**, hence most are used with `sudo`.

### Why Users and Groups Are Important
- Users represent **people or services**
- Groups allow **multiple users to share access**
- Permissions are always evaluated as:
  1. Owner
  2. Group
  3. Others



### `sudo`
```bash
sudo command
```
**Description:** Executes command as **root (administrator)**. Required for system-level changes.

---

### `adduser`
```bash
sudo adduser username
```
**Description:** Creates a new user account.

---

### `passwd`
```bash
passwd
sudo passwd username
```
**Description:** Sets or changes user password.

---

### `userdel`
```bash
sudo userdel username
```
**Description:** Deletes a user account.

---

### `groupadd`
```bash
sudo groupadd groupname
```
**Description:** Creates a new group.

---

### `usermod -aG`
```bash
sudo usermod -aG group user
```
**Description:** Adds user to a group.
- `-a` → append (do not remove existing groups)
- `-G` → group name

---

### `su`
```bash
su - username
```
**Description:** Switches to another user.

---

## 6️⃣ FILE SEARCH & TEXT PROCESSING

> These commands are used to **search files, search text, and analyze file content**. Very common in exams.



### `find`
```bash
find / -name file.txt
```
**Description:** Searches files/directories.

---

### `grep`
```bash
grep "word" file.txt
grep -i "word" file.txt
```
**Description:** Searches text in files.
- `-i` → ignore case

---

### `wc`
```bash
wc file.txt
wc -l file.txt
```
**Description:** Counts lines, words, characters.

---

### `diff`
```bash
diff file1 file2
```
**Description:** Compares two files line by line.

---

## 7️⃣ PROCESS & SYSTEM COMMANDS

> These commands help you **monitor and control the system**.

### Important Process Concepts
- Every running program has a **PID (Process ID)**
- Processes can be foreground or background
- Zombie and orphan processes may appear in theory questions



### `ps`
```bash
ps
ps -ef
```
**Description:** Displays running processes.

---

### `top`
```bash
top
```
**Description:** Shows real-time system processes.

---

### `kill`
```bash
kill PID
kill -9 PID
```
**Description:** Terminates processes.

---

### `df`
```bash
df -h
```
**Description:** Displays disk usage.

---

### `du`
```bash
du -sh dir
```
**Description:** Displays directory size.

---

## 8️⃣ ARCHIVE & BACKUP COMMANDS

### `tar`
```bash
tar -cvf backup.tar dir
tar -xvf backup.tar
```
**Description:** Creates and extracts archives.

---

### `gzip / gunzip`
```bash
gzip file
gunzip file.gz
```
**Description:** Compresses and decompresses files.

---

## 9️⃣ NETWORKING & SYSTEM INFO

### `uname`
```bash
uname -a
```
**Description:** Displays system information.

---

### `hostname`
```bash
hostname
```
**Description:** Displays system hostname.

---

### `ping`
```bash
ping google.com
```
**Description:** Checks network connectivity.

---

## 🔟 PACKAGE MANAGEMENT (UBUNTU)

> Used to **install, update, and remove software**. Requires internet and root access.

### Common apt Workflow
1. Update package list
2. Install required package
3. Remove unused packages



### `apt`
```bash
sudo apt update
sudo apt install package
sudo apt remove package
```
**Description:** Installs, updates, removes software packages.

---

## 🧠 EXAM GOLDEN RULES

- Always start scripts with `#!/bin/bash`
- No space in variable assignment (`x=10`)
- Use `sudo` for admin tasks
- Use `chmod` for permissions, `chown` for ownership
- Test permissions using `su - user`

---

## ✅ END OF CHEAT SHEET

This file is **complete, detailed, and exam-ready**.

