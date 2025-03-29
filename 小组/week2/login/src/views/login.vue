<template>
    <div class="auth-container">
     <h1 class="auth-title">登录系统</h1>
      <div class="auth-card"> 
        <div class="auth-tabs">
          <button 
            @click="activeTab = 'login'"
            :class="{ 'active': activeTab === 'login' }"
          >
            登录
          </button>
          <button 
            @click="activeTab = 'register'"
            :class="{ 'active': activeTab === 'register' }"
          >
            注册
          </button>
        </div>
  
        <!-- 登录表单 -->
        <form 
          v-if="activeTab === 'login'" 
          @submit.prevent="handleLogin"
          class="auth-form"
        >
          <div class="form-group">
            <label for="login-email">邮箱/手机号</label>
            <input
              id="login-email"
              v-model="login.email"
              type="text"
              placeholder="请输入邮箱或手机号"
              @blur="validateLoginEmail"
            >
            <span class="error" v-if="loginErrors.email">{{ loginErrors.email }}</span>
          </div>
  
          <div class="form-group">
            <label for="login-password">密码</label>
            <input
              id="login-password"
              v-model="login.password"
              type="password"
              placeholder="请输入密码"
              @blur="validateLoginPassword"
            >
            <span class="error" v-if="loginErrors.password">{{ loginErrors.password }}</span>
          </div>
  
          <button type="submit" class="submit-btn">登录</button>
        </form>
  
        <!-- 注册表单 -->
        <form 
          v-else 
          @submit.prevent="handleRegister"
          class="auth-form"
        >
          <div class="form-group"> 
            <label for="register-email">邮箱</label>
            <input
              id="register-email"
              v-model="register.email"
              type="email"
              placeholder="请输入邮箱"
              @blur="validateEmail"
            >
            <span class="error" v-if="registerErrors.email">{{ registerErrors.email }}</span>
          </div>
  
          <div class="form-group">
            <label for="register-phone">手机号</label>
            <input
              id="register-phone"
              v-model="register.phone"
              type="tel"
              placeholder="请输入手机号"
              @blur="validatePhone"
            >
            <span class="error" v-if="registerErrors.phone">{{ registerErrors.phone }}</span>
          </div>
  
          <div class="form-group">
            <label for="register-password">密码</label>
            <input
              id="register-password"
              v-model="register.password"
              type="password"
              placeholder="请输入密码"
              @blur="validatePassword"
            >
            <span class="error" v-if="registerErrors.password">{{ registerErrors.password }}</span>
          </div>
  
          <div class="form-group">
            <label for="register-confirm-password">确认密码</label>
            <input
              id="register-confirm-password"
              v-model="register.confirmPassword"
              type="password"
              placeholder="请再次输入密码"
              @blur="validateConfirmPassword"
            >
            <span class="error" v-if="registerErrors.confirmPassword">{{ registerErrors.confirmPassword }}</span>
          </div>
  
          <button type="submit" class="submit-btn">注册</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AuthForm',
    data() {
      return {
        activeTab: 'login',
        login: {
          email: '',
          password: ''
        },
        register: {
          email: '',
          phone: '',
          password: '',
          confirmPassword: ''
        },
        loginErrors: {
          email: '',
          password: ''
        },
        registerErrors: {
          email: '',
          phone: '',
          password: '',
          confirmPassword: ''
        }
      }
    },
    methods: {
      handleLogin() {
        if (this.validateLoginForm()) {
          alert('登录成功！');
          // 这里可以添加实际的登录逻辑
        }
      },
      handleRegister() {
        if (this.validateRegisterForm()) {
          alert('注册成功！');
          // 这里可以添加实际的注册逻辑
        }
      },
      validateLoginForm() {
        let isValid = true;
        
        if (!this.login.email) {
          this.loginErrors.email = '请输入邮箱或手机号';
          isValid = false;
        } else {
          this.loginErrors.email = '';
        }
        
        if (!this.login.password) {
          this.loginErrors.password = '请输入密码';
          isValid = false;
        } else {
          this.loginErrors.password = '';
        }
        
        return isValid;
      },
      validateRegisterForm() {
        let isValid = true;
        
        if (!this.register.email) {
          this.registerErrors.email = '请输入邮箱';
          isValid = false;
        } else if (!this.isValidEmail(this.register.email)) {
          this.registerErrors.email = '邮箱格式不正确';
          isValid = false;
        } else {
          this.registerErrors.email = '';
        }
        
        if (!this.register.phone) {
          this.registerErrors.phone = '请输入手机号';
          isValid = false;
        } else if (!this.isValidPhone(this.register.phone)) {
          this.registerErrors.phone = '手机号格式不正确';
          isValid = false;
        } else {
          this.registerErrors.phone = '';
        }
        
        if (!this.register.password) {
          this.registerErrors.password = '请输入密码';
          isValid = false;
        } else if (this.register.password.length < 6) {
          this.registerErrors.password = '密码长度不能少于6位';
          isValid = false;
        } else {
          this.registerErrors.password = '';
        }
        
        if (!this.register.confirmPassword) {
          this.registerErrors.confirmPassword = '请确认密码';
          isValid = false;
        } else if (this.register.password !== this.register.confirmPassword) {
          this.registerErrors.confirmPassword = '两次输入的密码不一致';
          isValid = false;
        } else {
          this.registerErrors.confirmPassword = '';
        }
        
        return isValid;
      },
      validateLoginEmail() {
        if (!this.login.email) {
          this.loginErrors.email = '请输入邮箱或手机号';
        } else {
          this.loginErrors.email = '';
        }
      },
      validateLoginPassword() {
        if (!this.login.password) {
          this.loginErrors.password = '请输入密码';
        } else {
          this.loginErrors.password = '';
        }
      },
      validateEmail() {
        if (!this.register.email) {
          this.registerErrors.email = '请输入邮箱';
        } else if (!this.isValidEmail(this.register.email)) {
          this.registerErrors.email = '邮箱格式不正确';
        } else {
          this.registerErrors.email = '';
        }
      },
      validatePhone() {
        if (!this.register.phone) {
          this.registerErrors.phone = '请输入手机号';
        } else if (!this.isValidPhone(this.register.phone)) {
          this.registerErrors.phone = '手机号格式不正确';
        } else {
          this.registerErrors.phone = '';
        }
      },
      validatePassword() {
        if (!this.register.password) {
          this.registerErrors.password = '请输入密码';
        } else if (this.register.password.length < 6) {
          this.registerErrors.password = '密码长度不能少于6位';
        } else {
          this.registerErrors.password = '';
        }
      },
      validateConfirmPassword() {
        if (!this.register.confirmPassword) {
          this.registerErrors.confirmPassword = '请确认密码';
        } else if (this.register.password !== this.register.confirmPassword) {
          this.registerErrors.confirmPassword = '两次输入的密码不一致';
        } else {
          this.registerErrors.confirmPassword = '';
        }
      },
      isValidEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
      },
      isValidPhone(phone) {
        const re = /^1[3-9]\d{9}$/;
        return re.test(phone);
      }
    }
  }
  </script>
  
  <style scoped>
  .auth-container {
    display: flex;
    flex-direction: column; /* 改为垂直布局 */
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f5f5f5;
    font-family: 'Arial', sans-serif;
    padding: 20px; /* 添加内边距 */
  }
  
  .auth-title {
    font-size: 28px;
    font-weight: bold;
    color: #333;
    margin-bottom: 30px;
    text-align: center;
  }
  
  .auth-card {
    width: 100%;
    max-width: 500px; /* 增大卡片宽度 */
    background: white;
    border-radius: 12px; /* 增大圆角 */
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* 增强阴影 */
    overflow: hidden;
  }
  
  .auth-tabs {
    display: flex;
    border-bottom: 1px solid #eee;
  }
  
  .auth-tabs button {
    flex: 1;
    padding: 18px; /* 增大内边距 */
    border: none;
    background: none;
    cursor: pointer;
    font-size: 18px; /* 增大字体 */
    font-weight: 600;
    color: #666;
    transition: all 0.3s ease;
  }
  
  .auth-tabs button.active {
    color: #2bbbcb;
    border-bottom: 3px solid #2bbbcb; /* 加粗激活状态边框 */
  }
  
  .auth-form {
    padding: 30px; /* 增大内边距 */
  }
  
  .form-group {
    margin-bottom: 25px; /* 增大间距 */
  }
  
  .form-group label {
    display: block;
    margin-bottom: 10px; /* 增大间距 */
    font-size: 16px; /* 增大字体 */
    color: #555;
  }
  
  .form-group input {
    width: 100%;
    padding: 12px; /* 增大内边距 */
    border: 1px solid #ddd;
    border-radius: 6px; /* 增大圆角 */
    font-size: 16px; /* 增大字体 */
    transition: border-color 0.3s;
  }
  
  .form-group input:focus {
    border-color: #2bbbcb;
    outline: none;
    box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2); /* 添加聚焦阴影 */
  }
  
  .error {
    color: #f56c6c;
    font-size: 14px; /* 增大字体 */
    margin-top: 8px; /* 增大间距 */
    display: block;
  }
  
  .submit-btn {
    width: 100%;
    padding: 14px; /* 增大内边距 */
    background-color: #2bbbcb;
    color: white;
    border: none;
    border-radius: 6px; /* 增大圆角 */
    font-size: 18px; /* 增大字体 */
    cursor: pointer;
    transition: all 0.3s; /* 改为所有属性过渡 */
  }
  
  .submit-btn:hover {
    background-color: #0fd5d9;
    transform: translateY(-2px); /* 添加悬停上浮效果 */
    box-shadow: 0 4px 12px rgba(102, 177, 255, 0.3); /* 添加悬停阴影 */
  }
</style>