import React from 'react';
import AppointmentCard from './AppointmentCard';
import { useAuth } from '../../contexts/AuthContext';

const AppointmentList = ({ appointments, onStatusChange }) => {
  const { user } = useAuth();
  const isAdmin = user?.role === 'admin';

  return (
    <div className="space-y-6">
      {appointments.map((appointment) => (
        <AppointmentCard
          key={appointment.id}
          appointment={appointment}
          onStatusChange={onStatusChange}
          isAdmin={isAdmin}
        />
      ))}
    </div>
  );
};

export default AppointmentList;