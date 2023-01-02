**Doctor Patient REST APIs implemented using Spring Boot for CRUD operations**

How to Setup project

To run locally:

1. Setup mysql database - Please refer below file.
   a. Local database configurations done in `applications.properties`

   b. Docker configurations in `docker-compose.yaml`

2. Build the project:
    -  `mvn clean install`

3. Run the application
    - Use an IDE and run the application, or
    - `java -jar doctor-patient-crud.jar`


To run in docker compose

4. Docker compose settings

   a. Config files :
    - Dockerfile
    - docker-compose.yaml

   b. To start the application : Open the terminal and run  `docker compose up --build`

   c. To stop the application : Run `docker compose down`

Once the above steps are completed, use POSTMAN to run below APIs.


REST APIs Endpoints

There are patient CRUD and doctor CRUD API's available in the project. Please find below.


PATIENT CRUD API's

1. Add a patient

POST /addPatient

Accept: application/json

Content-Type: application/json

`{
"patientId": 10,
"patientName": "Sachin Tendulkar",
"dob": "1973-04-24T13:37:27.000+00:00",
"address": "Mumbai",
"contactNo": "100000",
"disease": "Master Blasting"
}`

2. Get all Patient details

GET /getAllPatients

Accept: application/json

Content-Type: application/json

3. Get Patient details by Id

GET /getPatient/{patientId}

Accept: application/json

Content-Type: application/json


4. Update Patient details

PUT /updatePatient/{patientId}
   
Accept: application/json 

Content-Type: application/json

`{
"patientId": 24,
"patientName": "Sourav Ganguly",
"dob": "1973-07-08T01:00:00.000+00:00",
"address": "Kolkata",
"contactNo": "183239",
"disease": "Styilish Sixers"
}`


5. Delete Patient details

DELETE /deletePatient/{patientId}

Accept: application/json

Content-Type: application/json


6. Allocate a doctor to patient

PUT /{patientId}/allocateDoctor/{doctorId}

Accept: application/json

Content-Type: application/json



DOCTOR CRUD API's

1. Add a doctor

POST /addDoctor

Accept: application/json

Content-Type: application/json

{`
"doctorId": 1,
"doctorName": "Dr. Kapil Dev",
"patients": []
}`

2. Get all doctor details

GET /getAllDoctors

Accept: application/json

Content-Type: application/json

3. Get doctor details by Id

GET /getDoctor/{doctorId}

Accept: application/json

Content-Type: application/json


4. Update doctor details

PUT /updateDoctor/{doctorId}

Accept: application/json 

Content-Type: application/json

`{
"doctorId": 1,
"doctorName": "Dr. Dr.Kapil Dev Indian",
"patients": []
}`


5. Delete Doctor details

DELETE /deleteDoctor/{doctorId}

Accept: application/json

Content-Type: application/json


6. Allocate a doctor to patient

PUT /{doctorId}/deAllocate/{patientId}

Accept: application/json

Content-Type: application/json

Get common information

1. Health check 
GET / 

Eg: http://localhost:8080/
