import React, {useEffect, useState} from 'react';
import axios from 'axios';
//import '../styles/Mystudents.css'
//import AddmyStudents from "../modals/AddmyStudents"

function App() {
  const [lectures, setLecture] = useState('');

  useEffect(() => {
    axios.get('/eple/v1/mystudent/lecture')
        .then(response => setLecture(response.data))
        .catch(error => console.log(error))
  }, []);


  return (
      <div>
        <div className="PageNameMystudents">
          <p>MyStudents</p>

        </div>
        {lectures.lectureTitle}
        {lectures.fee}


        </div>


  );
}

export default App;
