import React, { useEffect, useState } from 'react';
import { useAppointments } from '../../hooks/useAppointments';

const TimeSlotPicker = ({ value, onChange, date, doctorId }) => {
  const { getAvailableTimeSlots } = useAppointments();
  const [availableSlots, setAvailableSlots] = useState([]);

  useEffect(() => {
    if (date && doctorId) {
      const slots = getAvailableTimeSlots(date, doctorId);
      setAvailableSlots(slots);
    }
  }, [date, doctorId]);

  return (
    <div>
      <label className="block text-sm font-medium text-gray-700">Select Time</label>
      <select
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
        required
      >
        <option value="">Choose a time slot</option>
        {availableSlots.map((slot) => (
          <option key={slot.value} value={slot.value}>
            {slot.label}
          </option>
        ))}
      </select>
    </div>
  );
};

export default TimeSlotPicker;