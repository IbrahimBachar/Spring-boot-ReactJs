import { useState } from 'react';

export const useDoctors = () => {
  // This would typically fetch from an API
  const [doctors] = useState([
    {
      id: '1',
      name: 'John Smith',
      specialization: 'Cardiologist',
      availability: ['Monday', 'Wednesday', 'Friday']
    },
    {
      id: '2',
      name: 'Sarah Johnson',
      specialization: 'Pediatrician',
      availability: ['Tuesday', 'Thursday', 'Saturday']
    },
    {
      id: '3',
      name: 'Michael Brown',
      specialization: 'Dermatologist',
      availability: ['Monday', 'Tuesday', 'Thursday']
    }
  ]);

  return { doctors };
};

export default useDoctors;