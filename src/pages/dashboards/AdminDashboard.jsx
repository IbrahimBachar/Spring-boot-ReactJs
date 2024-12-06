import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import AppointmentList from '../../components/appointments/AppointmentList';
import Pagination from '../../components/Pagination';
import { useAppointments } from '../../hooks/useAppointments';

const ITEMS_PER_PAGE = 5;

const AdminDashboard = () => {
  const { t } = useTranslation();
  const { appointments, updateAppointmentStatus } = useAppointments();
  const [searchTerm, setSearchTerm] = useState('');
  const [statusFilter, setStatusFilter] = useState('all');
  const [sortBy, setSortBy] = useState('date');
  const [currentPage, setCurrentPage] = useState(1);

  const filteredAppointments = appointments
    .filter(apt => {
      const matchesSearch = 
        apt.patientName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        apt.doctorName?.toLowerCase().includes(searchTerm.toLowerCase());
      const matchesStatus = statusFilter === 'all' || apt.status === statusFilter;
      return matchesSearch && matchesStatus;
    })
    .sort((a, b) => {
      if (sortBy === 'date') return new Date(a.date) - new Date(b.date);
      if (sortBy === 'status') return a.status.localeCompare(b.status);
      return 0;
    });

  const totalPages = Math.ceil(filteredAppointments.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const paginatedAppointments = filteredAppointments.slice(
    startIndex,
    startIndex + ITEMS_PER_PAGE
  );

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-900 mb-6">
          {t('admin.title')}
        </h1>
        
        <div className="bg-white rounded-lg shadow-md p-6 mb-6">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <input
              type="text"
              placeholder={t('admin.search')}
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
            />
            
            <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
              className="rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
            >
              <option value="all">{t('admin.status.all')}</option>
              <option value="pending">{t('admin.status.pending')}</option>
              <option value="approved">{t('admin.status.approved')}</option>
              <option value="cancelled">{t('admin.status.cancelled')}</option>
            </select>
            
            <select
              value={sortBy}
              onChange={(e) => setSortBy(e.target.value)}
              className="rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
            >
              <option value="date">{t('admin.sort.date')}</option>
              <option value="status">{t('admin.sort.status')}</option>
            </select>
          </div>
        </div>

        <AppointmentList
          appointments={paginatedAppointments}
          onStatusChange={updateAppointmentStatus}
        />

        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={setCurrentPage}
        />
      </div>
    </div>
  );
};

export default AdminDashboard;