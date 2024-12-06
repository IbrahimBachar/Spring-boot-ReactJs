import React, { createContext, useContext, useState } from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const { loginWithRedirect, logout: auth0Logout, user: auth0User, isAuthenticated } = useAuth0();

  const login = async (email, password) => {
    // Implement regular login logic here
    console.log('Regular login:', { email, password });
    // For demo, simulate successful login
    setUser({ email, role: 'patient' });
    navigate('/dashboard/patient');
  };

  const logout = () => {
    setUser(null);
    auth0Logout({ returnTo: window.location.origin });
  };

  // Handle Auth0 user
  React.useEffect(() => {
    if (isAuthenticated && auth0User) {
      setUser({
        email: auth0User.email,
        role: 'patient', // Default role for social login users
        name: auth0User.name,
        picture: auth0User.picture
      });
      navigate('/dashboard/patient');
    }
  }, [isAuthenticated, auth0User, navigate]);

  return (
    <AuthContext.Provider value={{ user, login, logout, loginWithRedirect }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);