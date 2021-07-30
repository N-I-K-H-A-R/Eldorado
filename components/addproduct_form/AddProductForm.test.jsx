import React from 'react'
import AddProductForm from './AddProductForm'
import {shallow  ,configure} from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import  {render ,screen ,fireEvent} from '@testing-library/react'

configure({ adapter: new Adapter() });

test("renders",()=>{
    const wrapper = shallow(<AddProductForm />);
    expect(wrapper.exists()).toBe(true);
});

test("basic to do ",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter Product Name/i);
    expect(linkelement).toBeInTheDocument();
});

test("Checking Onchange Event for Product Name",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter Product Description/i);
    fireEvent.change(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Onchange Event for Price",() =>{
    render(<AddProductForm  />); 
    const linkelement= screen.getByPlaceholderText(/Enter Price/i);
    fireEvent.change(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Onchange Event for Quantity",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter the Quantity/i);
    fireEvent.change(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Onchange Event for Image Link",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter Image Links/i);
    fireEvent.click(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Onchange Event for Video Link",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter Youtube Video Links/i);
    fireEvent.click(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Onchange Event for PDF Link",() =>{
    render(<AddProductForm />); 
    const linkelement= screen.getByPlaceholderText(/Enter PDF Link/i);
    fireEvent.change(linkelement, {target: { value: "grocery"}})
    expect(linkelement.value).toBe("grocery");
});

test("Checking Add product button", () =>{
    const wrapper = shallow(<AddProductForm />);
    expect(wrapper.find("Button").text()).toBe("Add Product");
});

test("Checking Form tag contents", () => {
    const wrapper = shallow(<AddProductForm />);
    expect(wrapper.find("Form").length).toEqual(1);
});
