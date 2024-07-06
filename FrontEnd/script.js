// Get the form elements
import axios from 'axios';

const form = document.querySelector('form');
const subjectSelect = document.querySelector('#subject');
const topicInput = document.querySelector('#topic');

// Add an event listener to the form submit event
form.addEventListener('submit', (e) => {
  e.preventDefault(); // Prevent the default form submission behavior

  // Get the selected subject and topic values
  const subject = subjectSelect.value;
  const topic = topicInput.value;

  // Create a new Axios instance
  const axiosInstance = axios.create({
    baseURL: 'https://www.google.com/', // Replace with your API URL
  });

  // Send a POST request to the server
  axiosInstance.post('/track-productivity', {
    subject,
    topic,
  })
 .then((response) => {
    console.log(response.data);
    // Handle the response data here
  })
 .catch((error) => {
    console.error(error);
    // Handle the error here
  });
});