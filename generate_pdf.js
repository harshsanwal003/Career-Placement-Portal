const PDFDocument = require('pdfkit');
const fs = require('fs');

const doc = new PDFDocument({ margin: 50 });
doc.pipe(fs.createWriteStream('Project_Defence_Document.pdf'));

// Fonts and Styling
doc.font('Helvetica-Bold').fontSize(20).text('Project Defense Document', { align: 'center' });
doc.moveDown();
doc.font('Helvetica').fontSize(12).text('AI-Powered Career Match & Placement Portal', { align: 'center' });
doc.moveDown(2);

// Section 1: Workflow of Information & AI Inputs
doc.font('Helvetica-Bold').fontSize(16).text('1. Workflow of Information & User Inputs');
doc.moveDown(0.5);

doc.font('Helvetica-Bold').fontSize(12).text('Information Workflow (Architecture):');
doc.font('Helvetica').fontSize(11).text('The application follows a standard Spring Boot MVC/Layered architecture:');
doc.list([
  'Frontend (HTML/CSS/JS): Sends HTTP requests (e.g., login, register) via REST APIs.',
  'Controller Layer: Receives the requests, maps the endpoints, and passes data to the Service layer.',
  'Service Layer: Contains the core business logic (like OTP generation, JWT token validation, user matching).',
  'Repository Layer: Handles Data Access using Spring Data JPA.',
  'Database (MySQL): Stores persistent data (Users, Jobs, OTPs, etc.).'
], { indent: 20 });
doc.moveDown();

doc.font('Helvetica-Bold').fontSize(12).text('Inputs I Gave to the AI Assistant:');
doc.font('Helvetica').fontSize(11).text('As the lead architect, I provided the following instructions to the AI to generate the codebase:');
doc.list([
  'Requested a modern, premium "Dark Mode" UI redesign using glassmorphism and animations.',
  'Directed the implementation of a secure email-based OTP Authentication system.',
  'Requested standard Spring Boot boilerplate for Entity, Service, and Repository classes.',
  'Instructed the generation of a comprehensive project synopsis and dissertation text.',
  'Defined the roles (Admin, Job Seeker, Company) and their respective dashboard requirements.'
], { indent: 20 });
doc.moveDown(2);

// Section 2: Synopsis
doc.font('Helvetica-Bold').fontSize(16).text('2. Project Synopsis');
doc.moveDown(0.5);

doc.font('Helvetica-Bold').fontSize(12).text('Title:');
doc.font('Helvetica').fontSize(11).text('AI-powered Career Match & Placement Portal');
doc.moveDown(0.5);

doc.font('Helvetica-Bold').fontSize(12).text('Objective:');
doc.font('Helvetica').fontSize(11).text('To develop an intelligent, secure, and highly interactive platform that bridges the gap between job seekers and companies. It streamlines the hiring process by matching candidate profiles with job requirements while ensuring high security through JWT and OTP verification.');
doc.moveDown(0.5);

doc.font('Helvetica-Bold').fontSize(12).text('Tech Stack:');
doc.list([
  'Backend: Java, Spring Boot, Spring Security (JWT), Spring Mail (OTP)',
  'Frontend: Vanilla HTML, CSS (Glassmorphism UI), JavaScript',
  'Database: MySQL / Spring Data JPA'
], { indent: 20 });
doc.moveDown(2);

// Section 3: Where is the Code for Each Process (Overview)
doc.font('Helvetica-Bold').fontSize(16).text('3. Code Locations Overview (Process Mapping)');
doc.moveDown(0.5);

const codeLocations = [
  { process: 'Database Tables & Data Structure', file: 'src/main/java/.../entity/ (e.g., Job.java, User.java)' },
  { process: 'Authentication & Token Generation', file: 'src/main/java/.../security/JwtUtil.java' },
  { process: 'Email & OTP Sending Logic', file: 'src/main/java/.../service/EmailService.java' },
  { process: 'Business Logic (Users, Matching)', file: 'src/main/java/.../service/ (e.g., JobSeekerService.java)' },
  { process: 'API Endpoints & Request Routing', file: 'src/main/java/.../controller/' },
  { process: 'Database Queries & Saves', file: 'src/main/java/.../repository/ (e.g., JobSeekerRepository.java)' },
  { process: 'User Interface & Visuals', file: 'src/main/resources/static/ (HTML files and style.css)' },
  { process: 'App Configuration & Credentials', file: 'src/main/resources/application.properties' }
];

codeLocations.forEach(item => {
  doc.font('Helvetica-Bold').fontSize(11).text(`${item.process}: `);
  doc.font('Helvetica').fontSize(10).text(`--> ${item.file}`);
  doc.moveDown(0.5);
});
doc.moveDown(2);

// Conclusion
doc.font('Helvetica-Oblique').fontSize(11).text('Note to Evaluator: The AI assistant was used as a coding tool for syntax, styling, and boilerplate generation under the direct architectural guidance, conceptualization, and orchestration of the student.', { align: 'center' });

doc.end();
console.log("PDF generated successfully!");
