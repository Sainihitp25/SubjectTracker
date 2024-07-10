# SubjectTrackerMS1

SubjectTrackerMS1 is a microservice for managing subjects, providing endpoints to get all subjects, add a new subject, delete a subject by ID, and get a subject by ID.

## Endpoints

### Get All Subjects

- **URL**: `http://localhost:9092/subjects`
- **Method**: `GET`
- **Description**: Retrieves a list of all subjects.

### Add a New Subject

- **URL**: `http://localhost:9092/subject`
- **Method**: `POST`
- **Description**: Adds a new subject.
- **Body** (raw JSON):
    ```json
    {
      "Subject": "Maths",
      "Topic": "Algebra"
    }
    ```

### Delete Subject by ID

- **URL**: `http://localhost:9092/subject/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a subject by its ID.

### Get Subject by ID

- **URL**: `http://localhost:9092/subject/{id}`
- **Method**: `GET`
- **Description**: Retrieves a subject by its ID.



