# OUOU Accessories — Backend API Documentation (for Frontend)

**Base URL:** `http://localhost:8084`
**Auth server (Keycloak realm):** `ouou` @ `http://localhost:8080/realms/ouou`

## Authentication & Roles

All endpoints require a **Bearer JWT** issued by Keycloak (except `/api/v1/auth/**`).
Roles are extracted from the JWT resource_access claim:
- `client_admin` — admin user (back-office)
- `client_user` — regular user (front-office)

### 1. Admin Login Page
`POST /api/v1/auth/login` — **public**, no token required.

Request:
```json
{ "username": "admin_user", "password": "admin_pass", "role": "admin" }
```
Response 200:
```json
{
  "access_token": "eyJhbGciOi...",
  "token_type": "Bearer",
  "expires_in": 300,
  "refresh_token": "eyJhbGciOi...",
  "role": "admin",
  "username": "admin_user"
}
```
Response 401 if credentials are invalid.

> Store `access_token` in the front (localStorage / sessionStorage) and send it as
> `Authorization: Bearer <token>` on every protected request.

---

## Endpoints Summary

| Method | URL                                                | Roles                        | Purpose |
|--------|---------------------------------------------------|------------------------------|---------|
| POST   | `/api/v1/auth/login`                              | public                       | Admin login (returns JWT) |
| GET    | `/api/v1/auth/me`                                 | authenticated                | Token info |
| GET    | `/api/v1/categorie/getcategories`                 | client_user, client_admin    | List categories |
| POST   | `/api/v1/categorie/savecategorie`                 | client_admin                 | Create category |
| PUT    | `/api/v1/categorie/updatecategorie/{id}`          | client_admin                 | Update category |
| DELETE | `/api/v1/categorie/deletecategorie/{id}`          | client_admin                 | Delete category |
| GET    | `/api/v1/product/getProducts`                     | client_user, client_admin    | List products |
| GET    | `/api/v1/product/getProductById/{id}`             | client_user, client_admin    | Get one product |
| POST   | `/api/v1/product/saveProduct`                     | client_admin                 | Create product (JSON) |
| POST   | `/api/v1/product/saveProduct` (multipart)         | client_admin                 | Create product with image |
| PUT    | `/api/v1/product/updateProduct/{id}`              | client_admin                 | Update product |
| DELETE | `/api/v1/product/deleteProduct/{id}`              | client_admin                 | Delete product |
| GET    | `/api/v1/commande/getCommands`                    | client_admin                 | List orders |
| GET    | `/api/v1/commande/getCommandeById/{id}`           | client_admin                 | Get one order |
| POST   | `/api/v1/commande/saveCommand`                    | client_user, client_admin    | Create order |
| GET    | `/api/v1/getusers`                                | client_admin                 | List clients |
| POST   | `/api/v1/saveuser`                                | client_user, client_admin    | Create client |
| PUT    | `/api/v1/updateuser`                              | client_admin                 | Update client |
| DELETE | `/api/v1/deleteuser/{id}`                         | client_admin                 | Delete client |

---

## DTOs (request / response shapes)

### CategorieDTO
```json
{ "id": 1, "name": "Bracelets" }
```

### ProductDTO
```json
{
  "id": 1,
  "name": "Bracelet argent",
  "description": "...",
  "price": "120.00",
  "qteStock": "10",
  "image": "<base64 byte[] or empty>",
  "categorie": { "id": 1, "name": "Bracelets" }
}
```

### CommandeDTO (order)
```json
{
  "id": 1,
  "name": "CMD-001",
  "date": "2026-09-04",
  "quantity": "2",
  "total": "240.00",
  "status": "PENDING",
  "delivred": "NO",
  "clientTel": "+212600000000",
  "nameClient": "Amir",
  "lastnameClient": "Baba",
  "product": { "id": 1 },
  "client":  { "id": 1 }
}
```

### ClientDTO
```json
{ "id": 1, "name": "Amir" }
```

---

## Frontend Pages

### A. Admin Login Page (`/admin/login`)
- Form fields: `username`, `password`.
- `POST /api/v1/auth/login` with body `{username, password, role: "admin"}`.
- On success: store `access_token`, redirect to `/admin/dashboard`.
- On 401: show "Identifiants invalides".

### B. Admin Dashboard (`/admin`)
- Sidebar / tabs:
  - **Categories** — list + add + edit + delete (calls `api/v1/categorie/*`).
  - **Products** — list + add (with image upload via `multipart/form-data`) + edit + delete.
  - **Orders (Commandes)** — list + view details.
  - **Clients** — list + delete.
- All requests send header: `Authorization: Bearer <access_token>`.
- On 401/403 response: redirect to `/admin/login`.

### C. User (front-office)
- `/products` — call `GET /api/v1/product/getProducts` (public to authenticated users).
- `/categories` — call `GET /api/v1/categorie/getcategories`.
- Place order form — `POST /api/v1/commande/saveCommand`.

---

## Error Responses
- `401 Unauthorized` — missing/invalid token.
- `403 Forbidden` — role not allowed for endpoint.
- `404 Not Found` — entity not found.
- `500` — server error.

---

## Quick cURL examples

```bash
# Login
curl -X POST http://localhost:8084/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin","role":"admin"}'

# List products (with token)
curl http://localhost:8084/api/v1/product/getProducts \
  -H "Authorization: Bearer <ACCESS_TOKEN>"

# Create category (admin)
curl -X POST http://localhost:8084/api/v1/categorie/savecategorie \
  -H "Authorization: Bearer <ACCESS_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"name":"Colliers"}'

# Place order (user)
curl -X POST http://localhost:8084/api/v1/commande/saveCommand \
  -H "Authorization: Bearer <USER_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"name":"CMD-1","quantity":"1","total":"120.00","status":"PENDING","product":{"id":1},"client":{"id":1}}'
```