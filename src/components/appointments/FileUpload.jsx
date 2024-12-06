import React, { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import { useTranslation } from 'react-i18next';

const FileUpload = ({ onFileUpload }) => {
  const { t } = useTranslation();

  const onDrop = useCallback((acceptedFiles) => {
    onFileUpload(acceptedFiles);
  }, [onFileUpload]);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    accept: {
      'image/*': ['.jpeg', '.jpg', '.png'],
      'application/pdf': ['.pdf']
    },
    maxSize: 5242880, // 5MB
    multiple: true
  });

  return (
    <div className="mt-4">
      <label className="block text-sm font-medium text-gray-700 mb-2">
        {t('appointment.uploadFiles')}
      </label>
      <div
        {...getRootProps()}
        className={`p-6 border-2 border-dashed rounded-lg text-center cursor-pointer
          ${isDragActive ? 'border-blue-500 bg-blue-50' : 'border-gray-300 hover:border-blue-400'}`}
      >
        <input {...getInputProps()} />
        {isDragActive ? (
          <p className="text-blue-500">{t('appointment.dropFiles')}</p>
        ) : (
          <div>
            <p>{t('appointment.dragFiles')}</p>
            <p className="text-sm text-gray-500 mt-1">{t('appointment.fileTypes')}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default FileUpload;