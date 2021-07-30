import React from 'react'
import Navigationbar from './Navigationbar'
import {shallow  ,configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import  {render ,screen } from '@testing-library/react'

configure({ adapter: new Adapter() });

  test("renders navigationbar",() =>{
      const wrapper = shallow(<Navigationbar />);
      expect(wrapper.exists()).toBe(true);
    });
  test("includes 1 image",() =>{
      const wrapper = shallow(<Navigationbar />);
      expect(wrapper.find("img").length).toEqual(1);
    });

  test("Search bar",() =>{
      render(<Navigationbar />); 
      const linkelement= screen.getByPlaceholderText(/Search/i);
      expect(linkelement).toBeInTheDocument();
    });

      
      
  