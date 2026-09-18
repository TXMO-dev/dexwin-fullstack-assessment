
- Location: TaskService:42-44
- Status: observed 
- Evidence: A raw string SQL query is string concatenated into a native query 
            so anyone can do '/api/search?q=%2dSELECT * FROM' and it will go through with is an SQL injection vulnerability
- Impact: Severe
- Priority: High
- Proposed solution: we used a built in method from the hibernate orm to resolve sql injection issues
- Verification:
- Implementation notes:

---

- Location: TaskService:26 - 38
- Status: observed 
- Evidence: There is an N + 1 query issue happening within that process where task.getAssignee().getUsername() and task.getComments().size() will execute a query per each task.
- Impact: Medium
- Priority: Medium
- Proposed solution: we used lazy loading to avoid eagerloading on each query task by changing the fetchtype to Lazy in the entities
- Verification:
- Implementation notes:

---

- Location: User:25
- Status: observed 
- Evidence: The password is exposed there is no @JsonIgnore or @JsonProperty(access=WRITE_ONLY) so any endpoint response returns the password or even the setup of DTOs
- Impact: HIGH
- Priority: HIGH
- Proposed solution:aside the use of JsonIgnore and jsonProperty with write access of WriteOnly we also introduce BCrypt for password hashing
- Verification:
- Implementation notes:

---

---

- Location: Project:25
- Status: observed 
- Evidence:one to many columns are not lazy loaded to avoid N + 1 queries.
- Impact: HIGH
- Priority: HIGH
- Proposed solution: to avoid eager loading issues
- Verification:
- Implementation notes:

---

- Location: AuthController
- Status: observed 
- Evidence: No use of DTOs so entities are being used as @RequestBody which is prone to information the client doesnt really need
propose solution: Introduce dtos
---

- Location: login
- Status: observed 
- Evidence: there are no tokens being returned, no use of BCrypt for password hashing
- Proposed solution: Introduce JWT or cookie sessions depending on the platform and also concepts like Identity token, access token, refresh token, etc
---

- Location: TaskItem.tsx:15
- Status: observed 
- Evidence: it should be {task.title} and not {task.name}
- proposed solution: changetask.name to task.title

---

- Location: TaskItem.tsx:15
- Status: observed 
- Evidence: the function will not render due to useState immutability by nature.
- proposed we fixed it by manipulating the derived state and evidence was shown in the UI
---

UI INTERACTION
- When clicking on the complete button of a task item nothing happens, https://fantastic-eureka-7547pgw6pqqf94w-5173.app.github.dev/api/tasks/1/status?status=TODO
we are getting a 200 OK however nothing is showing on the screen (Fixed at TaskItem:15)
- The UI is not mobile responsive. (Use Tailwind or css media queries)
- even when i click on reopen and the taskitem card is disabled still the endpoint is triggered and i receive a 200 OK. (dixed in TaskItem:15)