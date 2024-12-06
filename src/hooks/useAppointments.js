import { useState, useCallback } from 'react';

export const useAppointments = () => {
  const [appointments, setAppointments] = useState([]);

  const getAvailableTimeSlots = useCallback((date, doctorId) => {
    // This would typically be an API call
    const timeSlots = [
      { value: '09:00', label: '9:00 AM' },
      { value: '10:00', label: '10:00 AM' },
      { value: '11:00', label: '11:00 AM' },
      { value: '14:00', label: '2:00 PM' },
      { value: '15:00', label: '3:00 PM' },
      { value: '16:00', label: '4:00 PM' },
    ];

    // Filter out already booked slots
    return timeSlots.filter(slot => {
      return !appointments.some(apt => 
        apt.date === date && 
        apt.timeSlot === slot.value && 
        apt.doctorId === doctorId
      );
    });
  }, [appointments]);

  const createAppointment = useCallback((appointmentData) => {
    const newAppointment = {
      id: Date.now().toString(),
      ...appointmentData,
      createdAt: new Date().toISOString()
    };
    setAppointments(prev => [...prev, newAppointment]);
    return newAppointment;
  }, []);

  const updateAppointmentStatus = useCallback((appointmentId, status) => {
    setAppointments(prev => 
      prev.map(apt => 
        apt.id === appointmentId ? { ...apt, status } : apt
      )
    );
  }, []);

  return {
    appointments,
    getAvailableTimeSlots,
    createAppointment,
    updateAppointmentStatus
  };
};

export default useAppointments;