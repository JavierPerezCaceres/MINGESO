import axios from "axios";
 
export default axios.create({
  baseURL: "http://161.35.60.79:8081",
  headers: {
    "Content-type": "application/json",
  }
});
