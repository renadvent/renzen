import {useState} from "react";

const [data,setData] = useState("loading");



    client({method: 'GET', path: '/api/employees'}).then(response => {
        setData(response.entity._embedded.employees)
    })


export default data;