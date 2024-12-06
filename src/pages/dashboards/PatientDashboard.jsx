import React from 'react';
import AppointmentForm from '../../components/appointments/AppointmentForm';
import AppointmentList from '../../components/appointments/AppointmentList';
import { useAppointments } from '../../hooks/useAppointments';
import { useAuth } from '../../contexts/AuthContext';

const PatientDashboard = () => {
  const { user } = useAuth();
  const { appointments, createAppointment } = useAppointments();

  const handleAppointmentSubmit = (appointmentData) => {
    createAppointment(appointmentData);
  };

  const userAppointments = appointments.filter(apt => apt.patientId === user?.id);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-7xl mx-auto">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div>
            <h1 className="text-3xl font-bold text-gray-900 mb-6">Book an Appointment</h1>
            <AppointmentForm onSubmit={handleAppointmentSubmit} />
          </div>
          <div>
            <h1 className="text-3xl font-bold text-gray-900 mb-6">Your Appointments</h1>
            <AppointmentList appointments={userAppointments} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default PatientDashboard;