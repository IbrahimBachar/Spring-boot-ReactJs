import React from 'react';
import { useAuth } from '../contexts/AuthContext';
import { FaGoogle, FaFacebook } from 'react-icons/fa';

const SocialLogin = () => {
  const { loginWithRedirect } = useAuth();

  const handleGoogleLogin = () => {
    loginWithRedirect({
      connection: 'google-oauth2'
    });
  };

  const handleFacebookLogin = () => {
    loginWithRedirect({
      connection: 'facebook'
    });
  };

  return (
    <div className="mt-6">
      <div className="relative">
        <div className="absolute inset-0 flex items-center">
          <div className="w-full border-t border-gray-300" />
        </div>
        <div className="relative flex justify-center text-sm">
          <span className="px-2 bg-white text-gray-500">Or continue with</span>
        </div>
      </div>

      <div className="mt-6 grid grid-cols-2 gap-3">
        <button
          onClick={handleGoogleLogin}
          className="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
        >
          <FaGoogle className="h-5 w-5 text-red-500" />
          <span className="ml-2">Google</span>
        </button>

        <button
          onClick={handleFacebookLogin}
          className="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
        >
          <FaFacebook className="h-5 w-5 text-blue-600" />
          <span className="ml-2">Facebook</span>
        </button>
      </div>
    </div>
  );
};

export default SocialLogin;