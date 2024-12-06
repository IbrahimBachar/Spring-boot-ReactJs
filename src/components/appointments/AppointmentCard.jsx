import React from 'react';
import { format } from 'date-fns';
import { useTranslation } from 'react-i18next';

const AppointmentCard = ({ appointment, onStatusChange, isAdmin }) => {
  const { t } = useTranslation();
  const statusColors = {
    pending: 'bg-yellow-100 text-yellow-800',
    approved: 'bg-green-100 text-green-800',
    cancelled: 'bg-red-100 text-red-800'
  };

  const handleFileDownload = (file) => {
    // Create a blob URL for the file and trigger download
    const url = URL.createObjectURL(file);
    const a = document.createElement('a');
    a.href = url;
    a.download = file.name;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <div className="flex justify-between items-start">
        <div>
          <h3 className="text-lg font-semibold">
            {t('appointment.with', { doctor: appointment.doctorName })}
          </h3>
          <p className="text-gray-600">
            {format(new Date(appointment.date), 'MMMM d, yyyy')} at {appointment.timeSlot}
          </p>
          <p className="text-gray-600 mt-2">{appointment.reason}</p>
          
          {appointment.files?.length > 0 && (
            <div className="mt-4">
              <h4 className="text-sm font-medium text-gray-700 mb-2">
                {t('appointment.attachedFiles')}
              </h4>
              <div className="flex flex-wrap gap-2">
                {appointment.files.map((file, index) => (
                  <button
                    key={index}
                    onClick={() => handleFileDownload(file)}
                    className="inline-flex items-center px-3 py-1 rounded-full text-sm bg-blue-100 text-blue-700 hover:bg-blue-200"
                  >
                    {file.name}
                  </button>
                ))}
              </div>
            </div>
          )}
        </div>
        <span className={`px-3 py-1 rounded-full text-sm ${statusColors[appointment.status]}`}>
          {t(`appointment.status.${appointment.status}`)}
        </span>
      </div>
      
      {isAdmin && appointment.status === 'pending' && (
        <div className="mt-4 flex space-x-4">
          <button
            onClick={() => onStatusChange(appointment.id, 'approved')}
            className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
          >
            {t('appointment.approve')}
          </button>
          <button
            onClick={() => onStatusChange(appointment.id, 'cancelled')}
            className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
          >
            {t('appointment.cancel')}
          </button>
        </div>
      )}
    </div>
  );
};

export default AppointmentCard;