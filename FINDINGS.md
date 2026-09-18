
- Location: TaskService:42-44
- Status: observed 
- Evidence: A raw string SQL query is string concatenated into a native query 
            so anyone can do '/api/search?q=%2dSELECT * FROM' and it will go through with is an SQL injection vulnerability
- Impact: Severe
- Priority: High
- Proposed solution: 
- Verification:
- Implementation notes:

---

- Location: TaskService:26 - 38
- Status: observed 
- Evidence: There is an N + 1 query issue happening within that process where task.getAssignee().getUsername() and task.getComments().size() will execute a query per each task.
- Impact: Medium
- Priority: Medium
- Proposed solution:
- Verification:
- Implementation notes:

---

- Location: User:25
- Status: observed 
- Evidence: The password is exposed there is no @JsonIgnore or @JsonProperty(access=WRITE_ONLY) so any endpoint response returns the password or even the setup of DTOs
- Impact: HIGH
- Priority: HIGH
- Proposed solution:
- Verification:
- Implementation notes:

---

---

- Location: Project:25
- Status: observed 
- Evidence: Many to one columns are not lazy loaded to avoid N + 1 queries.
- Impact: HIGH
- Priority: HIGH
- Proposed solution:
- Verification:
- Implementation notes:

---

- Location: AuthController
- Status: observed 
- Evidence: No use of DTOs so entities are being used as @RequestBody which is prone to information the client doesnt really need

---

- Location: login
- Status: observed 
- Evidence: there are no tokens being returned, no use of BCrypt for password hashing

---

- Location: TaskItem.tsx:15
- Status: observed 
- Evidence: it should be {task.title} and not {task.name}

---

- Location: TaskItem.tsx:15
- Status: observed 
- Evidence: the function will not render due to useState immutability by nature.

---

UI INTERACTION
- When clicking on the complete button of a task item nothing happens, https://fantastic-eureka-7547pgw6pqqf94w-5173.app.github.dev/api/tasks/1/status?status=TODO
we are getting a 200 OK however nothing is showing on the screen
- The UI is not mobile responsive.
- even when i click on reopen and the taskitem card is disabled still the endpoint is triggered and i receive a 200 OK.