# AI-Powered Career Match & Placement Portal & AI Interview Agent 🚀

A comprehensive, state-of-the-art career preparation and placement ecosystem. This project comprises two main components that bridge the gap between job applications, resume parsing, skill matching, and AI-driven interview preparation:

1. **AI Career Match & Placement Portal (Backend & Portal Frontend)**: A Spring Boot application featuring secure JWT + OTP authentication, automated PDF resume parsing, instant skill matching, and recruiter dashboards.
2. **AI Interview Agent (React Frontend)**: An interactive Vite + React application integrated with the Gemini 1.5 Flash API that conducts live, adaptive technical/HR interviews and generates comprehensive performance scorecards.

---

## 📂 Repository Structure

```
├── career-portal/                  # Spring Boot Backend & Static Web Portal
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/career_portal/
│   │   │   │   ├── controller/      # REST API Controllers (Admin, Auth, Company, JobSeeker)
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── entity/          # JPA Hibernate Entities (User, JobSeeker, Company, Job, Application)
│   │   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   │   ├── security/        # JWT Authentication Filter & Spring Security Config
│   │   │   │   └── service/         # Business Logic (Email, Auth, Job, JobSeeker, Admin)
│   │   │   └── resources/
│   │   │       ├── static/          # Portal Web Interfaces (HTML, CSS, JS)
│   │   │       └── application.properties # Server, H2 DB, & Mail Configuration
│   │   └── test/                    # Unit & Integration Tests
│   ├── build.gradle                 # Gradle Project Configuration
│   ├── generate_pdf.js              # Project Synopsis & Dissertation PDF generator script
│   └── run.bat                      # Server boot script for Windows
│
├── ai-interview-agent/              # React + Vite Interview Simulator Frontend
│   ├── src/
│   │   ├── components/              # React Components (InterviewTerminal, EvaluationDashboard, etc.)
│   │   ├── services/
│   │   │   └── gemini.js            # Gemini API Integration & Sandbox mock question banks
│   │   ├── App.jsx                  # Root React Application
│   │   └── main.jsx                 # Entry Point
│   ├── package.json                 # Node.js dependencies & scripts
│   └── vite.config.js               # Vite Configuration
```

---

## ✨ Features

### 1. AI Career Match & Placement Portal
* **Visual Excellence**: Built with a dark-mode theme utilizing glassmorphism, responsive Bootstrap 5 layouts, and smooth HTML5 canvas particle/people-walking animations.
* **Double-Factor Secure Authentication**:
  * Traditional Email & Password login.
  * **One-Time Password (OTP)** login via Spring Mail (console fallback supported).
  * Stateless security using **JSON Web Tokens (JWT)** for all API requests.
* **Automated PDF Resume Parsing**:
  * Integrates **Apache PDFBox** to extract text from uploaded resumes.
  * Parses skills automatically and matches them against a dictionary.
  * Generates profile highlights (e.g., focus areas like Backend/Frontend, experience levels, main programming languages).
* **Smart Skill Match Score**:
  * Automatically calculates match percentage when applying for jobs based on candidate skills vs. job-required skills.
  * Sorts applicant pools in descending order of match scores on the recruiter's dashboard.
* **Role-Based Dashboards**:
  * **Job Seeker Dashboard**: Upload resumes, update skills, view matching jobs, and apply instantly.
  * **Company/Recruiter Dashboard**: Post jobs, view job applicants, and inspect their AI match scores.
  * **Admin Dashboard**: System telemetry, live metrics, and user management.

### 2. AI Interview Agent
* **Interactive Role Selection**: Practice interviews for tailored roles, including *AI/ML, Software Development, Frontend, Backend, Product Manager*, or a custom job description.
* **Simulated Interview Terminal**: A chat interface mimicking a strict, professional tech interviewer.
* **Dynamic Difficulty Adaptation**:
  * **Strong Answers**: The AI escalates the difficulty (Easy ➔ Medium ➔ Hard) to test boundaries.
  * **Weak/Vague Answers**: The AI highlights technical gaps and pivots to foundational questions.
  * **Timeouts/Skips**: Transition to a new topic seamlessly.
* **Sandbox Mode**: Fully functional offline/local mode with rich pre-defined question banks for users without a Gemini API Key.
* **Detailed Score Card & Dashboard**:
  * Overall performance score out of 10.
  * Specialized radar metrics (Technical Knowledge, Communication Skills, Confidence, Logic, Practicality).
  * Strengths, weaknesses, and missing topics highlighted.
  * Actionable, structured roadmap (STAR framework recommendations, immediate/short/medium-term milestones).

---

## 🛠️ Tech Stack & Dependencies

### Backend (Career Portal)
* **Language & Runtime**: Java 23 (compatible with Java 17+)
* **Framework**: Spring Boot 4.0.6, Spring Security, Spring Mail
* **Database**: H2 In-Memory Database (easily swappable to MySQL/PostgreSQL)
* **ORM**: Spring Data JPA & Hibernate
* **Libraries**: Lombok, Apache PDFBox (3.0.3), JJWT (Java JWT 0.11.5)
* **Build System**: Gradle Wrapper

### Frontend (AI Interview Agent)
* **Build Tool**: Vite
* **Library**: React 18
* **Styling**: Tailwind CSS / Custom CSS & Lucide React Icons
* **LLM Core**: Google Generative AI (Gemini 1.5 Flash API)

---

## ⚙️ Getting Started & Installation

### Prerequisites
* **Java Development Kit (JDK)** version 17 or 23 installed.
* **Node.js** (v18 or higher) and **npm** installed.

---

### Step 1: Set Up and Run the Backend (`career-portal`)

1. Navigate to the `career-portal` directory:
   ```bash
   cd career-portal
   ```
2. **Configure Mail Settings**:
   Open `src/main/resources/application.properties` and configure your SMTP credentials (if you wish to test live email OTP delivery):
   ```properties
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your.email@gmail.com
   spring.mail.password=your_app_password
   ```
   > **Note**: If mail credentials are left as default, OTPs will still print directly to the Spring Boot application console for testing.
   
3. **Build & Run the Application**:
   * **On Windows**: You can run the prepared script:
     ```cmd
     run.bat
     ```
   * **Or via Terminal**:
     ```bash
     ./gradlew bootRun
     ```
4. Access the portal index page: [http://localhost:8081](http://localhost:8081)
5. Access the in-memory **H2 Console** (username `sa`, password empty): [http://localhost:8081/h2-console](http://localhost:8081/h2-console) with the JDBC URL: `jdbc:h2:mem:career_portal`.

---

### Step 2: Set Up and Run the Frontend (`ai-interview-agent`)

1. Open a new terminal and navigate to the `ai-interview-agent` directory:
   ```bash
   cd ai-interview-agent
   ```
2. **Install node dependencies**:
   ```bash
   npm install
   ```
3. **Launch the development server**:
   ```bash
   npm run dev
   ```
4. Access the AI Interview Terminal in your browser at the address provided in your terminal (usually [http://localhost:5173](http://localhost:5173)).
5. Select a role, enter your Gemini API Key (or enter `sandbox` to play offline), and start your training session.

---

## 🧠 Key Code Implementations & Algorithms

### 1. Custom Resume Parser & Skill Extraction
Located in: [JobSeekerService.java](file:///c:/Users/A/OneDrive/Desktop/ag/career-portal/src/main/java/com/example/career_portal/service/JobSeekerService.java)
```java
// Uses Apache PDFBox to strip text from uploaded resumes
if (file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
    try (PDDocument document = Loader.loadPDF(file.getBytes())) {
        PDFTextStripper stripper = new PDFTextStripper();
        content = stripper.getText(document).toLowerCase();
    }
}

// Extracted skills are matched against a dictionary list and saved
List<String> knownSkills = Arrays.asList("java", "python", "spring", "react", "sql", "javascript", ...);
String extractedSkills = knownSkills.stream()
        .filter(content::contains)
        .collect(Collectors.joining(","));
```

### 2. Candidate Matching Score Logic
Located in: [JobService.java](file:///c:/Users/A/OneDrive/Desktop/ag/career-portal/src/main/java/com/example/career_portal/service/JobService.java)
Computes Jaccard-like keyword overlap between a candidate's skills and a job's requirements, outputting a value out of 100%:
```java
private double calculateScore(String seekerSkills, String requiredSkills) {
    if (seekerSkills == null || seekerSkills.isEmpty() || requiredSkills == null || requiredSkills.isEmpty()) {
        return 0.0;
    }
    Set<String> sSkills = Arrays.stream(seekerSkills.split("[,;\\s]+"))
            .map(String::trim).map(String::toLowerCase).collect(Collectors.toSet());
    Set<String> rSkills = Arrays.stream(requiredSkills.split("[,;\\s]+"))
            .map(String::trim).map(String::toLowerCase).collect(Collectors.toSet());

    long matched = rSkills.stream().filter(sSkills::contains).count();
    return ((double) matched / rSkills.size()) * 100.0;
}
```

### 3. Dynamic Interviewer Prompting (Gemini 1.5 Flash)
Located in: [gemini.js](file:///c:/Users/A/OneDrive/Desktop/ag/ai-interview-agent/src/services/gemini.js)
The prompt leverages strict JSON structure formatting:
```javascript
const prompt = `You are a strict, elite, and highly professional AI interviewer...
Interview History:
${historyText}

Strict Rules:
1. Analyze the candidate's last answer deeply. Assess specific technical depth, accuracy, logic, and confidence.
2. Adapt difficulty dynamically:
   - Strong answers: Increase difficulty to test boundaries.
   - Weak/vague answers: Highlight gaps and pivot to easier, foundational concepts.
3. Return your response strictly in JSON:
{
  "analysis": "1-sentence assessment...",
  "question": "Your next single interview question.",
  "suggestedDifficulty": "Easy, Medium, or Hard"
}`;
```

---

## 🔒 Security Architecture
1. **Password Hashing**: Done via Spring Security's `BCryptPasswordEncoder`.
2. **Stateless JWT Authorization**: Requests include the `Authorization: Bearer <Token>` header.
3. **JwtAuthFilter**: Extracts JWT token from requests, verifies signature using the configured secret key, and registers authentication into Spring's security context.
4. **OTP Verification**: Generates secure random 6-digit verification numbers, hashes/caches them temporarily, and triggers an email validation before generating a JWT token.

---

## 🏆 Project Defense Document (PDF Generator)
The repository contains a Node-based script `generate_pdf.js` inside `career-portal/` which uses `pdfkit` to generate a comprehensive `Project_Defence_Document.pdf`.
To run it:
```bash
cd career-portal
npm install pdfkit
node generate_pdf.js
```
This is useful for academic project reports or portfolio presentations.
