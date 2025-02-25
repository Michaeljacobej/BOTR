# 📝 Topic Management System

## 📌 Overview
This project is a **React + TypeScript + Vite** application with **Ant Design (Antd)** for the frontend, and a **Spring Boot + Spring Security** backend using **PostgreSQL** as the database. The system allows users to create topics, provide feedback through ratings, and ensures a fair voting mechanism with daily limits.

---

## 🏗 Tech Stack

### 🌐 Frontend
- **React** (Vite + TypeScript)
- **Ant Design (Antd)**
- **i18next** (for multilingual support)
- **Axios** (for API requests)

### 🔧 Backend
- **Spring Boot** (REST API)
- **Spring Security** (for authentication & authorization)
- **PostgreSQL** (database)
- **JWT Authentication**
- **Lombok** (to reduce boilerplate code)
- **Flyway** (for database migration)

---

## 🎯 Project Goals
1. 💡 **User Engagement:** Enable users to create topics and interact through ratings.
2. 🎯 **Feedback Mechanism:** Showcase like/dislike percentages for topics.
3. 🚫 **Limit User Actions:** Implement daily vote limits to ensure balanced participation.
4. 🌍 **Accessibility:** Support **English** and **Italian** languages for broader reach.

---

## 🚀 Features
- 📝 **Topic Creation:** Users can add new topics.
- 👍👎 **Topic Ratings:** Users can upvote or downvote topics.
- 📊 **Feedback Display:** View real-time percentage of likes/dislikes.
- 🔄 **Daily Vote Limit:** Prevent excessive voting to maintain fairness.
- 🌍 **Multilingual Support:** English & Italian translations available.
- 🔒 **Secure Authentication:** User login and role-based access control.

---

## 🛠 Setup & Installation
### 📦 Frontend
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/topic-management.git
   ```
2. Navigate to the frontend directory:
   ```sh
   cd frontend
   ```
3. Install dependencies:
   ```sh
   npm install
   ```
4. Run the application:
   ```sh
   npm run dev
   ```

### 🖥 Backend
1. Navigate to the backend directory:
   ```sh
   cd backend
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```

---

## 🌎 Localization (i18n)
This project supports **English** and **Italian** translations using `i18next`. To change the language, switch using the toggle in the UI.

---

## 📄 License
This project is licensed under the MIT License.

---

## 📩 Contact
If you have any questions or suggestions, feel free to reach out via email at **michaeljacob6789@gmail.com**.

Happy Coding! 🚀

