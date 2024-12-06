import React, { useState } from 'react';
import AppointmentList from '../../components/appointments/AppointmentList';
import { useAppointments } from '../../hooks/useAppointments';
import { useAuth } from '../../contexts/AuthContext';

const DoctorDashboard = () => {
  const { user } = useAuth();
  const { appointments } = useAppointments();
  const [filter, setFilter] = useState('all');

  const doctorAppointments = appointments.filter(apt => apt.doctorId === user?.id);
  
  const filteredAppointments = doctorAppointments.filter(apt => {
    if (filter === 'all') return true;
    return apt.status === filter;
  });

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-7xl mx-auto">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-900">Your Appointments</h1>
          <select
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
            className="rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          >
            <option value="all">All Appointments</option>
            <option value="pending">Pending</option>
            <option value="approved">Approved</option>
            <option value="cancelled">Cancelled</option>
          </select>
        </div>
        <AppointmentList appointments={filteredAppointments} />
      </div>
    </div>
  );
};

export default DoctorDashboard;