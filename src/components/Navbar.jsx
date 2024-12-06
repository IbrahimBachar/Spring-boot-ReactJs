import React from 'react';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import LanguageSwitcher from './LanguageSwitcher';

const Navbar = () => {
  const { t } = useTranslation();

  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <Link to="/" className="flex-shrink-0 flex items-center">
              <span className="text-xl font-bold text-blue-600">HealthCare Clinic</span>
            </Link>
          </div>
          <div className="flex items-center space-x-4">
            <LanguageSwitcher />
            <Link
              to="/login"
              className="px-4 py-2 text-gray-700 hover:text-blue-600 rounded-md"
            >
              {t('nav.login')}
            </Link>
            <Link
              to="/signup"
              className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            >
              {t('nav.signup')}
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;