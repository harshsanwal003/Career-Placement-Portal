const API_URL = '/api';

function getToken() {
    return localStorage.getItem('token');
}

function setToken(token) {
    localStorage.setItem('token', token);
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    window.location.href = '/index.html';
}

function authHeaders() {
    return {
        'Authorization': 'Bearer ' + getToken(),
        'Content-Type': 'application/json'
    };
}

async function login(email, password) {
    const res = await fetch(API_URL + '/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (!res.ok) throw new Error('Login failed');
    const data = await res.json();
    setToken(data.token);
    localStorage.setItem('role', data.role);
    redirectByRole(data.role);
}

async function register(name, email, password, role, companyName) {
    const res = await fetch(API_URL + '/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, password, role, companyName })
    });
    if (!res.ok) throw new Error('Registration failed');
    const data = await res.json();
    setToken(data.token);
    localStorage.setItem('role', data.role);
    redirectByRole(data.role);
}

function redirectByRole(role) {
    if (role === 'JOB_SEEKER') window.location.href = '/job-seeker-dashboard.html';
    else if (role === 'COMPANY') window.location.href = '/company-dashboard.html';
    else if (role === 'ADMIN') window.location.href = '/admin-dashboard.html';
}
