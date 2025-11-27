import axios from 'axios';
const baseUrl = "http://localhost:3333";

class StudentService {
    getAllStudent() {
        return axios.get(baseUrl + "/student/students");
    }

    addStudent(student) {
        let myHeader = { 'content-Type': 'application/json' };
        return axios.post(baseUrl + "/student/students", student, { headers: myHeader });
        //                    ^^^^^^^^^^^^^^^^^^^^^^^^^  no id in POST
    }

    updateStudent(student) {
        let myHeader = { 'content-Type': 'application/json' };
        return axios.put(baseUrl + "/student/students/" + student.sid, student, { headers: myHeader });
    }

    deleteStudent(id) {
        return axios.delete(baseUrl + "/student/students/" + id);
    }
}

export default new StudentService();
