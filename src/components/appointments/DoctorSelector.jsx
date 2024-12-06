import React from 'react';
import { useDoctors } from '../../hooks/useDoctors';

const DoctorSelector = ({ value, onChange }) => {
  const { doctors } = useDoctors();

  return (
    <div>
      <label className="block text-sm font-medium text-gray-700">Select Doctor</label>
      <select
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
        required
      >
        <option value="">Choose a doctor</option>
        {doctors.map((doctor) => (
          <option key={doctor.id} value={doctor.id}>
            Dr. {doctor.name} - {doctor.specialization}
          </option>
        ))}
      </select>
    </div>
  );
};

export default DoctorSelector;