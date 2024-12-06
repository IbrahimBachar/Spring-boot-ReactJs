import React, { useState } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { useTranslation } from 'react-i18next';
import DatePicker from './DatePicker';
import TimeSlotPicker from './TimeSlotPicker';
import DoctorSelector from './DoctorSelector';
import FileUpload from './FileUpload';
import FileList from './FileList';

const AppointmentForm = ({ onSubmit }) => {
  const { user } = useAuth();
  const { t } = useTranslation();
  const [appointment, setAppointment] = useState({
    date: '',
    timeSlot: '',
    doctorId: '',
    reason: '',
    patientId: user?.id,
    status: 'pending',
    files: []
  });

  const handleFileUpload = (files) => {
    setAppointment(prev => ({
      ...prev,
      files: [...prev.files, ...files]
    }));
  };

  const handleRemoveFile = (index) => {
    setAppointment(prev => ({
      ...prev,
      files: prev.files.filter((_, i) => i !== index)
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(appointment);
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h2 className="text-2xl font-semibold mb-6">{t('appointment.bookTitle')}</h2>
      <form onSubmit={handleSubmit} className="space-y-6">
        <DoctorSelector
          value={appointment.doctorId}
          onChange={(doctorId) => setAppointment({ ...appointment, doctorId })}
        />
        
        <DatePicker
          value={appointment.date}
          onChange={(date) => setAppointment({ ...appointment, date })}
        />
        
        <TimeSlotPicker
          value={appointment.timeSlot}
          onChange={(timeSlot) => setAppointment({ ...appointment, timeSlot })}
          date={appointment.date}
          doctorId={appointment.doctorId}
        />
        
        <div>
          <label className="block text-sm font-medium text-gray-700">
            {t('appointment.reason')}
          </label>
          <textarea
            value={appointment.reason}
            onChange={(e) => setAppointment({ ...appointment, reason: e.target.value })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
            rows="3"
            required
          />
        </div>

        <FileUpload onFileUpload={handleFileUpload} />
        <FileList files={appointment.files} onRemoveFile={handleRemoveFile} />

        <button
          type="submit"
          className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
        >
          {t('appointment.bookButton')}
        </button>
      </form>
    </div>
  );
};

export default AppointmentForm;