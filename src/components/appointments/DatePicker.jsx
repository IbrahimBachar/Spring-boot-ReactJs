import React from 'react';
import { addDays, format } from 'date-fns';

const DatePicker = ({ value, onChange }) => {
  const nextSevenDays = Array.from({ length: 7 }, (_, i) => {
    const date = addDays(new Date(), i + 1);
    return {
      value: format(date, 'yyyy-MM-dd'),
      label: format(date, 'EEEE, MMMM d')
    };
  });

  return (
    <div>
      <label className="block text-sm font-medium text-gray-700">Select Date</label>
      <select
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
        required
      >
        <option value="">Choose a date</option>
        {nextSevenDays.map((day) => (
          <option key={day.value} value={day.value}>
            {day.label}
          </option>
        ))}
      </select>
    </div>
  );
};

export default DatePicker;