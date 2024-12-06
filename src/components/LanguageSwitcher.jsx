import React from 'react';
import { useTranslation } from 'react-i18next';

const LanguageSwitcher = () => {
  const { i18n } = useTranslation();

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
  };

  return (
    <div className="flex space-x-2">
      <button
        onClick={() => changeLanguage('en')}
        className={`px-3 py-1 rounded-md ${
          i18n.language === 'en'
            ? 'bg-blue-600 text-white'
            : 'bg-gray-200 text-gray-700'
        }`}
      >
        EN
      </button>
      <button
        onClick={() => changeLanguage('fr')}
        className={`px-3 py-1 rounded-md ${
          i18n.language === 'fr'
            ? 'bg-blue-600 text-white'
            : 'bg-gray-200 text-gray-700'
        }`}
      >
        FR
      </button>
    </div>
  );
};

export default LanguageSwitcher;