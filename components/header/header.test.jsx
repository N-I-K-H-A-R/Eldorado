import React from 'react'
import Header from './Header'
import {shallow  ,configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';

configure({ adapter: new Adapter() });

test("renders header",()=>{
    const wrapper = shallow(<Header />);
    expect(wrapper.exists()).toBe(true);
});

test("includes 1 image", () => {
    const wrapper = shallow(<Header />);
    expect(wrapper.find("span").length).toEqual(1);
    });
