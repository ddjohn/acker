local M
do
id = 0
sda = 2
scl = 1
dev = 0x3F   -- I2C Address
reg = 0x00   -- write
i2c.setup(id, sda, scl, i2c.SLOW)
bl = 0x08      -- 0x08 = back light on
function send(data)
   value = {}
   for i = 1, #data do
      table.insert(value, data[i] + bl + 0x04 + rs)
      table.insert(value, data[i] + bl +  rs)      -- fall edge to write
   end
  
   i2c.start(id)
   i2c.address(id, dev ,i2c.TRANSMITTER)
   i2c.write(id, reg, value)
   i2c.stop(id)
end
if (rs == nil) then
 rs = 0
 send({0x30})
 tmr.delay(4100)
 send({0x30})
 tmr.delay(100)
 send({0x30})
 send({0x20, 0x20, 0x80})      -- 4 bit, 2 line
 send({0x00, 0x10})            -- display clear
 send({0x00, 0xc0})            -- display on
end
function cursor(op)
 oldrs=rs
 rs=0
 if (op == 1) then 
   send({0x00, 0xe0})            -- cursor on
  else 
   send({0x00, 0xc0})            -- cursor off
 end
 rs=oldrs
end
function cls()
 oldrs=rs
 rs=0
 send({0x00, 0x10})
 rs=oldrs
end
function home()
 oldrs=rs
 rs =0
 send({0x00, 0x20})
 rs=oldrs
end
function lcdprint (str,line,col)
if (type(str) =="number") then
 str = tostring(str)
end
rs = 0
if (line == 2) then
 send({0xc0,bit.lshift(col,4)})
elseif (line==1) then 
 send({0x80,bit.lshift(col,4)})
end
rs = 1
for i = 1, #str do
 char = string.byte(string.sub(str, i, i))
 send ({ bit.clear(char,0,1,2,3),bit.lshift(bit.clear(char,4,5,6,7),4)})
end
end
M = { lcdprint=lcdprint, cursor=cursor, cls=cls, home=home }
end
return M
