import React from 'react';
import { useTranslation } from 'react-i18next';

const FileList = ({ files, onRemoveFile }) => {
  const { t } = useTranslation();

  if (!files?.length) return null;

  return (
    <div className="mt-4">
      <h4 className="text-sm font-medium text-gray-700 mb-2">{t('appointment.uploadedFiles')}</h4>
      <ul className="space-y-2">
        {files.map((file, index) => (
          <li key={index} className="flex items-center justify-between p-2 bg-gray-50 rounded-md">
            <span className="text-sm text-gray-600">{file.name}</span>
            <button
              onClick={() => onRemoveFile(index)}
              className="text-red-500 hover:text-red-700"
            >
              {t('appointment.removeFile')}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FileList;